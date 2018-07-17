package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.CustomerDto;
import app.DTO.responseDTO.InventoryDto;
import app.DTO.responseDTO.RentResponseDto;
import app.entity.Customer;
import app.entity.Inventory;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import app.finder.CustomerFinder;
import app.DTO.requestDTO.CustomerDtoRequest;
import app.DTO.requestDTO.RentDto;
import app.service.CurrentTime;
import app.service.CustomerService;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
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
        CurrentTime.updateTime();
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

    @PostMapping(path = "/{id}/rent/{filmIds}")
    public @ResponseBody
    ResponseEntity<List<RentResponseDto>> rentAFilm(@PathVariable int id, @PathVariable List<Integer> filmIds) {
        List<Pair> rentResponsePairs = customerService.rentMultipleFilms(id, filmIds);
        HttpHeaders headers = new HttpHeaders();
        for (Pair pair : rentResponsePairs) {
            headers.add("Payload", "/rental/" + pair.getValue0().toString());
        }
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/return/{filmIds}")
    public @ResponseBody
    ResponseEntity<List<RentResponseDto>> returnAFilm(@PathVariable int id, @PathVariable List<Integer> filmIds) {
        List<Pair> returnResponsePairs  = customerService.returnMultipleFilms(id, filmIds);
        HttpHeaders headers = new HttpHeaders();
        for (Pair pair : returnResponsePairs) {
            headers.add("Payload", "/payment/" + pair.getValue0().toString());
        }
        return new ResponseEntity(headers, HttpStatus.OK);
    }
}
