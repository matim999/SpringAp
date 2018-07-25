package app.service;

import app.DTO.converter.BaseConverter;
import app.DTO.converter.ToBaseConverter;
import app.DTO.requestDTO.CustomerDtoRequest;
import app.DTO.requestDTO.PaymentDtoRequest;
import app.DTO.requestDTO.RentDto;
import app.DTO.requestDTO.RentalDtoRequest;
import app.DTO.responseDTO.AddressDto;
import app.DTO.responseDTO.CustomerDto;
import app.DTO.responseDTO.PaymentDto;
import app.DTO.responseDTO.RentalDto;
import app.ErrorCode;
import app.Markers;
import app.entity.*;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import app.repository.AddressRepository;
import app.repository.CustomerRepository;
import app.repository.StoreRepository;
import org.apache.commons.math3.util.Precision;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static net.logstash.logback.argument.StructuredArguments.value;


import java.text.MessageFormat;
import java.util.*;

import static app.ErrorCode.*;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class.getSimpleName());
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final StoreRepository storeRepository;
    private final RentalService rentalService;
    private final PaymentService paymentService;
    private final AddressService addressService;
    private final BaseConverter<Rental, RentalDto> rentalConverter;
    private final BaseConverter<Payment, PaymentDto> paymentConverter;
    private final ToBaseConverter<CustomerDtoRequest, CustomerDto> customerRequestConverter;
    private final BaseConverter<Customer, CustomerDto> customerConverter;
    private final InventoryChecker inventoryAvailabilityChecker;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository, StoreRepository storeRepository, RentalService rentalService, PaymentService paymentService, AddressService addressService, BaseConverter<Rental, RentalDto> rentalConverter, BaseConverter<Payment, PaymentDto> paymentConverter, ToBaseConverter<CustomerDtoRequest, CustomerDto> customerRequestConverter, BaseConverter<Customer, CustomerDto> customerConverter, InventoryAvailabilityChecker inventoryAvailabilityChecker) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.storeRepository = storeRepository;
        this.rentalService = rentalService;
        this.paymentService = paymentService;
        this.addressService = addressService;
        this.rentalConverter = rentalConverter;
        this.paymentConverter = paymentConverter;
        this.customerRequestConverter = customerRequestConverter;
        this.customerConverter = customerConverter;
        this.inventoryAvailabilityChecker = inventoryAvailabilityChecker;
    }

    public void addNewCustomer(CustomerDtoRequest customerDtoRequest) {
        Collection<Customer> collection = customerRepository.findAllByFirstNameAndLastName(customerDtoRequest.getFirstName(), customerDtoRequest.getLastName()).orElse(new ArrayList<>());
        if (collection.isEmpty()) {
            saveCustomer(customerDtoRequest);
            return;
        }
        CustomerDto customerDto = customerRequestConverter.convertAllToBase(customerDtoRequest);
        Collection<CustomerDto> customer = customerConverter.convertAll(collection);
        customer.forEach(a -> {
            if (a.equals(customerDto))
                throw new ConflictException("Customer with given data already exists", DIFFERENT);
        });
        saveCustomer(customerDtoRequest);
    }

    public void addCustomer(Customer customer) {
        CustomerDto customerDto = customerConverter.convertAll(customer);
        Collection<CustomerDto> existingCustomers = customerConverter.convertAll(customerRepository
                .findAllByFirstNameAndLastName(customerDto.getFirstName(), customerDto.getLastName()).orElse(new ArrayList<>()));
        if (!existingCustomers.isEmpty()) {
            if ( existingCustomers
                    .stream()
                    .filter(a -> a.equals(customerDto))
                    .findFirst()
                    .isPresent() )
                throw new ConflictException("This address already exists", ErrorCode.DIFFERENT);
        }
        checkForCustomer(customerDto);
    }


    private void saveCustomer(CustomerDtoRequest customerDtoRequest) {
        customerRepository.save(new Customer(customerRequestConverter.convertAllToBase(customerDtoRequest),
                addressRepository.findById(customerDtoRequest.getAddressId())
                        .orElseThrow(() -> new MyNotFoundException("Address Id not found", DIFFERENT))));
    }

    public void deleteCustomerByID(int id) {
        customerRepository.findById(id).orElseThrow(() -> new MyNotFoundException("No Actor With Given Id", DIFFERENT));
        customerRepository.deleteById(id);
    }

    Customer checkForCustomer(CustomerDto customerDto) {
        AddressDto addressDto = customerDto.getAddress();
        Collection<CustomerDto> existingCustomers = customerConverter.convertAll(customerRepository
                .findAllByFirstNameAndLastName(customerDto.getFirstName(), customerDto.getLastName()).orElse(new ArrayList<>()));
        if (existingCustomers.isEmpty()) {
            return saveCustomer(customerDto, addressDto);
        }
        return existingCustomers
                .stream()
                .filter(a -> a.equals(customerDto))
                .findFirst()
                .map(a -> customerRepository.findById(a.getCustomerId()).get())
                .orElseGet(() -> saveCustomer(customerDto, addressDto));
    }

    private Customer saveCustomer(CustomerDto customerDto, AddressDto addressDto) {
        Address address = addressService.checkForAddress(addressDto);
        return customerRepository.saveAndFlush(new Customer(customerDto, address));
    }

    public List<Pair> rentMultipleFilms(int id, List<Integer> filmIds) {
        Set<RentDto> rentDtos = new LinkedHashSet<>();
        List<Pair> rentPairs = new ArrayList<>();
        for (int i : filmIds) {
            rentDtos.add(new RentDto(id, i));
        }
        for (RentDto rentDto : rentDtos) {
            rentPairs.add(rentAFilm(rentDto));
        }
        return rentPairs;
    }

    private Pair<Integer, Integer> rentAFilm(RentDto rentDto) {
        Customer customer = customerRepository.findById(rentDto.getCustomerId())
                .orElseThrow(() -> new MyNotFoundException("Client with given ID not found", FILM_RENT_CUSTOMER_ID_NOT_FOUND));
        if (inventoryAvailabilityChecker.checkIfAlreadyRentedByThisCustomer(customer, rentDto.getFilmID()))
            throw new ConflictException(MessageFormat.format("This customer has already rented this film Id; {0}", rentDto.getFilmID()), FILM_RENT_FILM_ALREADY_RENTED_BY_CUSTOMER);
        Inventory inventory = inventoryAvailabilityChecker.checkAvailability(rentDto.getFilmID(), customer.getStoreId())
                .orElseThrow(() -> new MyNotFoundException("STH WRONG", DIFFERENT));
        Store store = storeRepository.findById(customer.getStoreId())
                .orElseThrow(() -> new MyNotFoundException("Customer Has wrong storeId", FILM_RENT_CUSTOMER_STORE_NOT_FOUND));
        Rental rental = rentalService.saveRentRental(createRentalRequestDto(customer, inventory, store));
        Payment payment = paymentService.saveRentPayment(createPaymentDtoRequest(customer, store, rental));
        logger.info(Markers.rentalMarker, "{}, {}, for customer {}, payment {}", value("action", "New film rental"),
                value("rental", rental.getRentalId()), value("customer", customer.getCustomerId()),
                value("payment", payment.getPaymentId()));
        return new Pair<>(rental.getRentalId(), payment.getPaymentId());
    }

    private RentalDtoRequest createRentalRequestDto(Customer customer, Inventory inventory, Store store) {
        return new RentalDtoRequest(CurrentTime.now,
                inventory.getInventoryId(),
                customer.getCustomerId(),
                null,
                store.getStaff().getStaffId());
    }

    private PaymentDtoRequest createPaymentDtoRequest(Customer customer, Store store, Rental rental) {
        return new PaymentDtoRequest(customer.getCustomerId(),
                store.getStaff().getStaffId(),
                rental.getRentalId(),
                rental.getInventory().getFilm().getRentalRate(),
                CurrentTime.now);
    }

    public List<Pair> returnMultipleFilms(int id, List<Integer> filmIds) {
        Set<RentDto> rentDtos = new LinkedHashSet<>();
        List<Pair> rentPairs = new ArrayList<>();
        for (int i : filmIds) {
            rentDtos.add(new RentDto(id, i));
        }
        for (RentDto rentDto : rentDtos) {
            rentPairs.add(returnAFilm(rentDto));
        }
        return rentPairs;
    }

    public Pair<Integer, Integer> returnAFilm(RentDto rentDto) {
        Customer customer = customerRepository.findById(rentDto.getCustomerId()).orElseThrow(() -> new MyNotFoundException("Client with given ID not found", FILM_RENT_CUSTOMER_ID_NOT_FOUND));
        Rental rental = inventoryAvailabilityChecker.FindRentalsForCustomerByFilmId(customer.getCustomerId(), rentDto.getFilmID());
        rentalService.updateRental(rental);
        Payment payment = null;
        if (DAYS.between(rental.getRentalDate().toLocalDate(), rental.getReturnDate().toLocalDate()) > rental.getInventory().getFilm().getRentalDuration()) {
            payment = returnAFilmRetentionFee(rental, customer);
        }
        logger.info(Markers.returnMarker, "{}, {}, for customer {}, payment {}", value("action", "Film return"),
                value("rental", rental.getRentalId()), value("customer", customer.getCustomerId()),
                value("payment", payment.getPaymentId()));
        return new Pair<>(rental.getRentalId(), payment != null ? payment.getPaymentId() : null);
    }

    private Payment returnAFilmRetentionFee(Rental rental, Customer customer) {
        Store store = storeRepository.findById(customer.getStoreId())
                .orElseThrow(() -> new MyNotFoundException("Customer Has wrong storeId", FILM_RENT_CUSTOMER_STORE_NOT_FOUND));
        PaymentDtoRequest paymentDtoRequest = new PaymentDtoRequest(customer.getCustomerId(),
                store.getStaff().getStaffId(),
                rental.getRentalId(),
                Precision.round(
                        (rental.getInventory().getFilm().getRentalRate() / 10 * (DAYS.between(rental.getRentalDate().toLocalDate(), rental.getReturnDate().toLocalDate()))),
                        2),
                CurrentTime.now);
        return paymentService.saveRentPayment(paymentDtoRequest);
    }

}
