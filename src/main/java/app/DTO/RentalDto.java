package app.DTO;

import lombok.Data;

public @Data
class RentalDto {
    private int rentalId;
    private String rentalDate;
    private InventoryDto inventory;
    private CustomerDto customer;
    private String returnDate;
    private StaffDto staff;
}
