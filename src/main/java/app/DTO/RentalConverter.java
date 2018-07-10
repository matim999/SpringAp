package app.DTO;

import app.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalConverter implements BaseConverter<Rental, RentalDto> {
    private final BaseConverter<Inventory, InventoryDto> inventoryConverter;
    private final BaseConverter<Customer, CustomerDto> customerConverter;
    private final BaseConverter<Staff, StaffDto> staffConverter;

    @Autowired
    public RentalConverter(BaseConverter<Inventory, InventoryDto> inventoryConverter, BaseConverter<Customer, CustomerDto> customerConverter, BaseConverter<Staff, StaffDto> staffConverter) {
        this.inventoryConverter = inventoryConverter;
        this.customerConverter = customerConverter;
        this.staffConverter = staffConverter;
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
}
