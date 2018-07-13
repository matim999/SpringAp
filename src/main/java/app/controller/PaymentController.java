package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.PaymentDto;
import app.entity.Payment;
import app.finder.PaymentFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentFinder paymentFinder;
    private final BaseConverter<Payment, PaymentDto> languageConverter;

    @Autowired
    public PaymentController(PaymentFinder paymentFinder, BaseConverter<Payment, PaymentDto> languageConverter) {
        this.paymentFinder = paymentFinder;
        this.languageConverter = languageConverter;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllPayment() {
        return new ResponseEntity(languageConverter.convertAll(paymentFinder.findAllPayment()), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Payment> findPaymentById(@PathVariable int id) {
        return new ResponseEntity(languageConverter.convertAll(paymentFinder.findPaymentById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllPaymentByAmount(@RequestParam int amount) {
        return new ResponseEntity(languageConverter.convertAll(paymentFinder.findAllPaymentByAmount(amount)), HttpStatus.OK);
    }
}
