package app.repository.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public @Data
@AllArgsConstructor
@NoArgsConstructor
class PaymentDtoRequest {
    private int customerId;
    private int staffId;
    private int rentalId;
    private double amount;
    private LocalDateTime paymentDate;
}
