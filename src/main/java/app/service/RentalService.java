package app.service;

import app.DTO.ErrorCode;
import app.DTO.converter.BaseConverter;
import app.DTO.converter.ToBaseConverter;
import app.DTO.requestDTO.RentalDtoRequest;
import app.DTO.responseDTO.AddressDto;
import app.DTO.responseDTO.CustomerDto;
import app.DTO.responseDTO.RentalDto;
import app.entity.Address;
import app.entity.Customer;
import app.entity.Rental;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import app.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final InventoryRepository inventoryRepository;
    private final ToBaseConverter<RentalDtoRequest, RentalDto> rentalRequestConverter;
    private final BaseConverter<Rental, RentalDto> rentalConverter;

    public RentalService(RentalRepository rentalRepository, CustomerRepository customerRepository, StaffRepository staffRepository, InventoryRepository inventoryRepository, ToBaseConverter<RentalDtoRequest, RentalDto> rentalRequestConverter, BaseConverter<Rental, RentalDto> rentalConverter) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.inventoryRepository = inventoryRepository;
        this.rentalRequestConverter = rentalRequestConverter;
        this.rentalConverter = rentalConverter;
    }

    public void addNewRental(RentalDtoRequest rentalDtoRequest){
        Collection<Rental> collection = rentalRepository.findAllByRentalDate(rentalDtoRequest.getRentalDate()).orElse(new ArrayList<>());
        if (collection.isEmpty()){
            saveRental(rentalDtoRequest);
            return;
        }
        RentalDto rentalDto = rentalRequestConverter.convertAllToBase(rentalDtoRequest);
        Collection<RentalDto> rental = rentalConverter.convertAll(collection);
        rental.forEach(a -> {
            if (a.equals(rentalDto)) {
                System.out.println(a.toString());
                System.out.println(rentalDto.toString());
                throw new ConflictException("This rental already exists", ErrorCode.DIFFERENT);
            }
        });
        saveRental(rentalDtoRequest);
    }

    private void saveRental(RentalDtoRequest rentalDtoRequest) {
        Rental rental = rentalRepository.saveAndFlush(new Rental(rentalRequestConverter.convertAllToBase(rentalDtoRequest),
                inventoryRepository.findById(rentalDtoRequest.getInventoryId()).orElseThrow(() -> new MyNotFoundException("Inventory with Given ID does'nt exist", ErrorCode.DIFFERENT)),
                customerRepository.findById(rentalDtoRequest.getCustomerId()).orElseThrow(() -> new MyNotFoundException("Inventory with Given ID does'nt exist", ErrorCode.DIFFERENT)),
                staffRepository.findById(rentalDtoRequest.getStaffId()).orElseThrow(() -> new MyNotFoundException("Inventory with Given ID does'nt exist", ErrorCode.DIFFERENT))));
        System.out.println(rental.toString());
    }

    public void deleteRentalByID(int id)
    {
        rentalRepository.findById(id).orElseThrow(() -> new MyNotFoundException("No Actor With Given Id", ErrorCode.DIFFERENT));
        rentalRepository.deleteById(id);
    }


}
