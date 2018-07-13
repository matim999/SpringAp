package app.DTO.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public @Data
@AllArgsConstructor
@NoArgsConstructor
class RentalDtoRequest {
    private LocalDateTime rentalDate;
    private int inventoryId;
    private int customerId;
    private LocalDateTime returnDate;
    private int staffId;
}
