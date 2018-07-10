package app.controller;

import app.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rental")
@EntityScan("app")
@ComponentScan("app")
public class RentalController {
    private final RentalRepository repo;

    @Autowired
    public RentalController(RentalRepository repo) {
        this.repo = repo;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    List getAllRental() {
        return repo.findAll();
    }

    @GetMapping(path="/specific/{id}")
    public @ResponseBody
    Optional getRental(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping(path="/specific")
        public @ResponseBody
        List GetRentalByCustomer(@RequestParam int customer_id) {
            return repo.findAllByCustomerCustomerId(customer_id);
        }
}
