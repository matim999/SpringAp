package app.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class RentalDto {
    @EqualsAndHashCode.Exclude private int rentalId;
    private String rentalDate;
    private InventoryDto inventory;
    private CustomerDto customer;
    private String returnDate;
    private StaffDto staff;
}
