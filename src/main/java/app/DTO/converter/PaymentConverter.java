package app.DTO.converter;

import app.DTO.responseDTO.CustomerDto;
import app.DTO.responseDTO.PaymentDto;
import app.DTO.responseDTO.RentalDto;
import app.DTO.responseDTO.StaffDto;
import app.entity.Customer;
import app.entity.Payment;
import app.entity.Rental;
import app.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter implements BaseConverter<Payment, PaymentDto> {
    private final BaseConverter<Customer, CustomerDto> customerConverter;
    private final BaseConverter<Staff, StaffDto> staffConverter;
    private final BaseConverter<Rental, RentalDto> rentalConverter;

    @Autowired
    public PaymentConverter(BaseConverter<Customer, CustomerDto> customerConverter, BaseConverter<Staff, StaffDto> staffConverter, BaseConverter<Rental, RentalDto> rentalConverter) {
        this.customerConverter = customerConverter;
        this.staffConverter = staffConverter;
        this.rentalConverter = rentalConverter;
    }

    @Override
    public PaymentDto convert(Payment from) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(from.getPaymentId());
        paymentDto.setCustomer(customerConverter.convertAll(from.getCustomer()));
        paymentDto.setStaff(staffConverter.convertAll(from.getStaff()));
        paymentDto.setRental(rentalConverter.convertAll(from.getRental()));
        paymentDto.setAmount(from.getAmount());
        paymentDto.setPaymentDate(from.getPaymentDate());
        return paymentDto;
    }
}
