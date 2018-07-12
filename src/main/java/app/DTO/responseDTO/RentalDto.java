package app.DTO.responseDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

public @Data
@JsonIgnoreProperties("actors")
class RentalDto {
    @EqualsAndHashCode.Exclude private int rentalId;
    private LocalDateTime rentalDate;
    private InventoryDto inventory;
    private CustomerDto customer;
    private LocalDateTime returnDate;
    private StaffDto staff;
}
