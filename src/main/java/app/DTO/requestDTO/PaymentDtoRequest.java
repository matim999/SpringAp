package app.DTO.requestDTO;

import app.DTO.responseDTO.CustomerDto;
import app.entity.Rental;
import app.entity.Staff;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class PaymentDtoRequest {
    private int customerId;
    private int staffId;
    private int rentalId;
    private int amount;
}
