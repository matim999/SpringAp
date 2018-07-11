package app.DTO.responseDTO;

import app.DTO.responseDTO.CustomerDto;
import app.entity.Rental;
import app.entity.Staff;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class PaymentDto {
    @EqualsAndHashCode.Exclude private int paymentId;
    private CustomerDto customer;
    private Staff staff;
    private Rental rental;
    private int amount;
}
