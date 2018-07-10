package app.controller;

import app.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentRepository repo;

    @Autowired
    public PaymentController(PaymentRepository repo) {
        this.repo = repo;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    List getAllPayment() {
        return repo.findAll();
    }

    @GetMapping(path="/specific")
    public @ResponseBody
    Optional getRentalById(@RequestParam int payment_id) {
        return repo.findById(payment_id);
    }
}
