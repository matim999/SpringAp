package app.controller;

import app.entity.Language;
import app.entity.Payment;
import app.finder.LanguageFinder;
import app.finder.PaymentFinder;
import app.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentFinder paymentFinder;

    @Autowired
    public PaymentController(PaymentFinder paymentFinder) {
        this.paymentFinder = paymentFinder;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllPayment()
    {
        return new ResponseEntity<>(paymentFinder.findAllPayment(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Payment> findPaymentById(@PathVariable int id) {
        return new ResponseEntity<>(paymentFinder.findPaymentById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllPaymentByAmount(@RequestParam int amount) {
        return new ResponseEntity<>(paymentFinder.findAllPaymentByAmount(amount), HttpStatus.OK);
    }
}
