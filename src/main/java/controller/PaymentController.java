package controller;

import app.Country;
import app.Payment;
import app.RepositoryCountry;
import app.RepositoryPayment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private RepositoryPayment repo;

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
