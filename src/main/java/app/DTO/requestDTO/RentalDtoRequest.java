package app.DTO.requestDTO;

import app.DTO.responseDTO.CustomerDto;
import app.DTO.responseDTO.InventoryDto;
import app.DTO.responseDTO.StaffDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class RentalDtoRequest {
    private String rentalDate;
    private int inventoryId;
    private int customerId;
    private String returnDate;
    private int staffId;
}
