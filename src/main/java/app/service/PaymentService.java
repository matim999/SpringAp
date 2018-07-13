package app.service;

import app.DTO.requestDTO.PaymentDtoRequest;
import app.ErrorCode;
import app.entity.Payment;
import app.exceptions.MyNotFoundException;
import app.repository.CustomerRepository;
import app.repository.PaymentRepository;
import app.repository.RentalRepository;
import app.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final RentalRepository rentalRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, CustomerRepository customerRepository, StaffRepository staffRepository, RentalRepository rentalRepository) {
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.rentalRepository = rentalRepository;
    }

    public Payment saveRentPayment(PaymentDtoRequest paymentDtoRequest) {
        return paymentRepository.save(new Payment(paymentDtoRequest,
                customerRepository.findById(paymentDtoRequest.getCustomerId()).orElseThrow(() -> new MyNotFoundException("SaveRentPayment: Customer with given ID not found", ErrorCode.DIFFERENT)),
                staffRepository.findById(paymentDtoRequest.getStaffId()).orElseThrow(() -> new MyNotFoundException("SaveRentPayment: Staff with given ID not found", ErrorCode.DIFFERENT)),
                rentalRepository.findById(paymentDtoRequest.getRentalId()).orElseThrow(() -> new MyNotFoundException("SaveRentPayment: Rental with given ID not found", ErrorCode.DIFFERENT))));
    }
}
