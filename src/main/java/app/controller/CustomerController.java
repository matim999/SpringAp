package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.requestDTO.CustomerDtoRequest;
import app.DTO.requestDTO.RentDto;
import app.DTO.responseDTO.CustomerDto;
import app.entity.Customer;
import app.finder.CustomerFinder;
import app.service.CustomerService;
import app.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerFinder categoryFinder;
    private final CustomerService customerService;
    private final RentService rentService;
    private final BaseConverter<Customer, CustomerDto> customerConverter;

    @Autowired
    public CustomerController(CustomerFinder categoryFinder, CustomerService customerService, RentService rentService, BaseConverter<Customer, CustomerDto> customerConverter) {
        this.categoryFinder = categoryFinder;
        this.customerService = customerService;
        this.rentService = rentService;
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
        return new ResponseEntity(customerConverter.convertAll(categoryFinder.findCustomerById(id)), HttpStatus.OK);
    }

    @PostMapping()
    public @ResponseBody
    ResponseEntity addNewCustomer(@RequestBody CustomerDtoRequest customerDtoRequest) {
        customerService.addNewCustomer(customerDtoRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity deleteCustomerByID(@PathVariable int id) {
        customerService.deleteCustomerByID(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findCustomerByFirstName(@RequestParam String name) {
        return new ResponseEntity(customerConverter.convertAll(categoryFinder.findAllCustomerByFirstName(name)), HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/rent/{filmId}")
    public @ResponseBody
    ResponseEntity rentAFilm(@PathVariable int id, @PathVariable int filmId) {
        RentDto rentDto = new RentDto(id, filmId);
//        Customer.rentAFilm();
//        rentService.rentAFilm();
        return new ResponseEntity(HttpStatus.OK);
    }
}
