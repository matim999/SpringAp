package app.DTO.responseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

public @Data
class PaymentDto {
    @EqualsAndHashCode.Exclude
    private int paymentId;
    private CustomerDto customer;
    private StaffDto staff;
    private RentalDto rental;
    private double amount;
    private LocalDateTime paymentDate;
}
