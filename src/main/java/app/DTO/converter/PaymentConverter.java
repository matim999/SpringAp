package app.DTO.converter;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.CustomerDto;
import app.DTO.responseDTO.PaymentDto;
import app.entity.Customer;
import app.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter implements BaseConverter<Payment, PaymentDto> {
    private final BaseConverter<Customer, CustomerDto> customerConverter;

    @Autowired
    public PaymentConverter(BaseConverter<Customer, CustomerDto> customerConverter) {
        this.customerConverter = customerConverter;
    }

    @Override
    public PaymentDto convert(Payment from) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(from.getPaymentId());
        paymentDto.setCustomer(customerConverter.convertAll(from.getCustomer()));
        return paymentDto;
    }
}
