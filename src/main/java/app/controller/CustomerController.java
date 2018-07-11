package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.CustomerDto;
import app.entity.Customer;
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
    private final BaseConverter<Customer, CustomerDto> customerConverter;

    @Autowired
    public CustomerController(CustomerFinder categoryFinder, BaseConverter<Customer, CustomerDto> customerConverter) {
        this.categoryFinder = categoryFinder;
        this.customerConverter = customerConverter;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List> findAllCustomer() {
        return new ResponseEntity(customerConverter.convertAll(categoryFinder.findAllCustomer()), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Customer> findCustomerById(@PathVariable int id) {
        return new ResponseEntity(customerConverter.convertAll(categoryFinder.findCustomeryById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findCustomerByFirstName(@RequestParam String name) {
        return new ResponseEntity(customerConverter.convertAll(categoryFinder.findAllCustomerByFirstName(name)), HttpStatus.OK);
    }
}
