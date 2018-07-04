package controller;

import app.Country;
import app.RepositoryCountry;
import app.RepositoryRental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rental")
@EntityScan("app")
@ComponentScan("app")
public class RentalController {
    @Autowired
    private RepositoryRental repo;

    @GetMapping(path="/all")
    public @ResponseBody
    List getAllRental() {
        return repo.findAll();
    }

    @GetMapping(path="/specific")
    public @ResponseBody
    Optional getRental(@RequestParam int rental_id) {
        return repo.findById(rental_id);
    }

    @GetMapping(path="/specificc")
        public @ResponseBody
        List GetRentalByCustomer(@RequestParam int customer_id) {
            return repo.findAllByCustomerId(customer_id);
        }
}
