package app.finder;

import app.entity.Language;
import app.entity.Payment;
import app.exceptions.MyNotFoundException;
import app.repository.LanguageRepository;
import app.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.DTO.ErrorCode.DIFFERENT;

@Component
public class PaymentFinder {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentFinder(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAllPayment()
    {
        return paymentRepository.findAll();
    }

    public Payment findPaymentById(int id){
        return paymentRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Category with given Id not found", DIFFERENT));
    }

    public List<Payment> findAllPaymentByAmount(int amount){
        return paymentRepository.findAllByAmount(amount).orElseThrow(() -> new MyNotFoundException("Category with given name not found", DIFFERENT));
    }
}
