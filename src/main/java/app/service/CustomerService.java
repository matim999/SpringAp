package app.service;

import app.ErrorCode;
import app.DTO.converter.BaseConverter;
import app.DTO.converter.ToBaseConverter;
import app.DTO.requestDTO.CustomerDtoRequest;
import app.DTO.requestDTO.RentDto;
import app.DTO.responseDTO.AddressDto;
import app.DTO.responseDTO.CustomerDto;
import app.entity.Address;
import app.entity.Customer;
import app.entity.Inventory;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import app.repository.AddressRepository;
import app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final AddressService addressService;
    private final ToBaseConverter<CustomerDtoRequest, CustomerDto> customerRequestConverter;
    private final BaseConverter<Customer, CustomerDto> customerConverter;
    private final InventoryAvailabilityChecker inventoryAvailabilityChecker;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository, AddressService addressService, ToBaseConverter<CustomerDtoRequest, CustomerDto> customerRequestConverter, BaseConverter<Customer, CustomerDto> customerConverter, InventoryAvailabilityChecker inventoryAvailabilityChecker) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.addressService = addressService;
        this.customerRequestConverter = customerRequestConverter;
        this.customerConverter = customerConverter;
        this.inventoryAvailabilityChecker = inventoryAvailabilityChecker;
    }

    public void addNewCustomer(CustomerDtoRequest customerDtoRequest)
    {
        Collection<Customer> collection = customerRepository.findAllByFirstNameAndLastName(customerDtoRequest.getFirstName(), customerDtoRequest.getLastName()).orElse(new ArrayList<>());
        if(collection.isEmpty()){
            saveCustomer(customerDtoRequest);
            return;
        }
        CustomerDto customerDto = customerRequestConverter.convertAllToBase(customerDtoRequest);
        Collection<CustomerDto> customer = customerConverter.convertAll(collection);
        customer.forEach(a -> {
            if (a.equals(customerDto))
                throw new ConflictException("Customer with given data already exists", ErrorCode.DIFFERENT);
        });
        saveCustomer(customerDtoRequest);
    }


    private void saveCustomer(CustomerDtoRequest customerDtoRequest) {
        customerRepository.save(new Customer(customerRequestConverter.convertAllToBase(customerDtoRequest),
                addressRepository.findById(customerDtoRequest.getAddressId())
                        .orElseThrow(() -> new MyNotFoundException("Address Id not found", ErrorCode.DIFFERENT))));
    }

    public void deleteCustomerByID(int id)
    {
        customerRepository.findById(id).orElseThrow(() -> new MyNotFoundException("No Actor With Given Id", ErrorCode.DIFFERENT));
        customerRepository.deleteById(id);
    }

    Customer checkForCustomer(CustomerDto customerDto){
        AddressDto addressDto = customerDto.getAddress();
        Collection<CustomerDto> existingCustomers = customerConverter.convertAll(customerRepository
                .findAllByFirstNameAndLastName(customerDto.getFirstName(), customerDto.getLastName()).orElse(new ArrayList<>()));
        if (existingCustomers.isEmpty()){
            return saveCustomer(customerDto, addressDto);
        }
        return existingCustomers
                .stream()
                .filter(a -> a.equals(customerDto))
                .findFirst()
                .map(a -> customerRepository.findById(a.getStoreId()).get())
                .orElseGet(() -> saveCustomer(customerDto, addressDto));
    }

    private Customer saveCustomer(CustomerDto customerDto, AddressDto addressDto) {
        Address address = addressService.checkForAddress(addressDto);
        return customerRepository.saveAndFlush(new Customer(customerDto, address));
    }

    public Inventory rentAFilm(RentDto rentDto){
        return inventoryAvailabilityChecker.checkAvailability(rentDto.getFilmID()).orElseThrow(() -> new MyNotFoundException("STH WRONG", ErrorCode.DIFFERENT));
    }
}
