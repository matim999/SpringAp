package app.controller;

import app.entity.Category;
import app.entity.Customer;
import app.finder.CategoryFinder;
import app.finder.CustomerFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerFinder categoryFinder;

    @Autowired
    public CustomerController(CustomerFinder categoryFinder) {
        this.categoryFinder = categoryFinder;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List> findAllCustomer() {
        return new ResponseEntity<>(categoryFinder.findAllCustomer(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Customer> findCustomerById(@PathVariable int id) {
        return new ResponseEntity<>(categoryFinder.findCustomeryById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findCustomerByFirstName(@RequestParam String name) {
        return new ResponseEntity<>(categoryFinder.findAllCustomerByFirstName(name), HttpStatus.OK);
    }
}
