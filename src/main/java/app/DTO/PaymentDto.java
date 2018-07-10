package app.DTO;

import app.entity.Customer;
import app.entity.Rental;
import app.entity.Staff;
import lombok.Data;

public @Data
class PaymentDto {
    private int paymentId;
    private CustomerDto customer;
    private Staff staff;
    private Rental rental;
    private int amount;
}
