package app.DTO.converter;

import app.DTO.requestDTO.RentalDtoRequest;
import app.DTO.responseDTO.CustomerDto;
import app.DTO.responseDTO.InventoryDto;
import app.DTO.responseDTO.RentalDto;
import app.DTO.responseDTO.StaffDto;
import app.entity.Customer;
import app.entity.Inventory;
import app.entity.Rental;
import app.entity.Staff;
import app.finder.CustomerFinder;
import app.finder.InventoryFinder;
import app.finder.StaffFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalConverter implements BaseConverter<Rental, RentalDto>, ToBaseConverter<RentalDtoRequest, RentalDto> {
    private final BaseConverter<Inventory, InventoryDto> inventoryConverter;
    private final BaseConverter<Customer, CustomerDto> customerConverter;
    private final BaseConverter<Staff, StaffDto> staffConverter;
    private final InventoryFinder inventoryFinder;
    private final CustomerFinder customerFinder;
    private final StaffFinder staffFinder;

    @Autowired
    public RentalConverter(BaseConverter<Inventory, InventoryDto> inventoryConverter, BaseConverter<Customer, CustomerDto> customerConverter, BaseConverter<Staff, StaffDto> staffConverter, InventoryFinder inventoryFinder, CustomerFinder customerFinder, StaffFinder staffFinder) {
        this.inventoryConverter = inventoryConverter;
        this.customerConverter = customerConverter;
        this.staffConverter = staffConverter;
        this.inventoryFinder = inventoryFinder;
        this.customerFinder = customerFinder;
        this.staffFinder = staffFinder;
    }


    @Override
    public RentalDto convert(Rental from) {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setRentalId(from.getRentalId());
        rentalDto.setRentalDate(from.getRentalDate());
        rentalDto.setInventory(inventoryConverter.convertAll(from.getInventory()));
        rentalDto.setCustomer(customerConverter.convertAll(from.getCustomer()));
        rentalDto.setReturnDate(from.getReturnDate());
        rentalDto.setStaff(staffConverter.convertAll(from.getStaff()));
        return rentalDto;
    }

    @Override
    public RentalDto convertToBase(RentalDtoRequest from) {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setRentalDate(from.getRentalDate());
        rentalDto.setInventory(inventoryConverter.convertAll(inventoryFinder.findInventoryById(from.getInventoryId())));
        rentalDto.setCustomer(customerConverter.convertAll(customerFinder.findCustomerById(from.getCustomerId())));
        rentalDto.setReturnDate(from.getReturnDate());
        rentalDto.setStaff(staffConverter.convertAll(staffFinder.findStaffById(from.getStaffId())));
        return rentalDto;
    }
}
