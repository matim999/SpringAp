package controller;

import app.Country;
import app.RepositoryCountry;
import app.RepositoryRental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rental")
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
        List GetRentalByCustomer(int customer_id) {
            customer_id = 300;
            return repo.findAllByInventory_id(customer_id);
        }
}
