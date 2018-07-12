package app.DTO.requestDTO;

import app.DTO.responseDTO.CustomerDto;
import app.DTO.responseDTO.InventoryDto;
import app.DTO.responseDTO.StaffDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

public @Data
class RentalDtoRequest {
    private LocalDateTime rentalDate;
    private int inventoryId;
    private int customerId;
    private LocalDateTime returnDate;
    private int staffId;
}
