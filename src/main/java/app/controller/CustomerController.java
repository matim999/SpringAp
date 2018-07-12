package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.requestDTO.CustomerDtoRequest;
import app.DTO.requestDTO.RentDto;
import app.DTO.responseDTO.CustomerDto;
import app.DTO.responseDTO.InventoryDto;
import app.entity.Customer;
import app.entity.Inventory;
import app.finder.CustomerFinder;
import app.service.CustomerService;
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
    private final BaseConverter<Customer, CustomerDto> customerConverter;
    private final BaseConverter<Inventory, InventoryDto> inventoryConverter;

    @Autowired
    public CustomerController(CustomerFinder categoryFinder, CustomerService customerService, BaseConverter<Customer, CustomerDto> customerConverter, BaseConverter<Inventory, InventoryDto> inventoryConverter) {
        this.categoryFinder = categoryFinder;
        this.customerService = customerService;
        this.customerConverter = customerConverter;
        this.inventoryConverter = inventoryConverter;
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

    @GetMapping(path = "/{id}/rent/{filmId}")
    public @ResponseBody
    ResponseEntity<Inventory> rentAFilm(@PathVariable int id, @PathVariable int filmId) {
        RentDto rentDto = new RentDto(id, filmId);
        Inventory inventory = customerService.rentAFilm(rentDto);
        return new ResponseEntity(inventoryConverter.convertAll(inventory), HttpStatus.OK);
    }
}
