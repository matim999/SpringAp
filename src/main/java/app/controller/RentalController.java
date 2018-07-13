package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.requestDTO.RentalDtoRequest;
import app.DTO.responseDTO.RentalDto;
import app.entity.Rental;
import app.finder.RentalFinder;
import app.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rental")
@EntityScan("app")
@ComponentScan("app")
public class RentalController {
    private final RentalFinder rentalFinder;
    private final RentalService rentalService;
    private final BaseConverter<Rental, RentalDto> rentalConverter;

    @Autowired
    public RentalController(RentalFinder rentalFinder, RentalService rentalService, BaseConverter<Rental, RentalDto> rentalConverter) {
        this.rentalFinder = rentalFinder;
        this.rentalService = rentalService;
        this.rentalConverter = rentalConverter;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllRental() {
        return new ResponseEntity(rentalConverter.convertAll(rentalFinder.findAllRental()), HttpStatus.OK);
    }

    @PostMapping
    private @ResponseBody
    ResponseEntity addNewRental(@RequestBody RentalDtoRequest rentalDtoRequest) {
        rentalService.addNewRental(rentalDtoRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Rental> findRentalById(@PathVariable int id) {
        return new ResponseEntity(rentalConverter.convertAll(rentalFinder.findRentalById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllRentalByRentalDate(@RequestParam LocalDateTime date) {
        return new ResponseEntity(rentalConverter.convertAll(rentalFinder.findAllRentalByRentalDate(date)), HttpStatus.OK);
    }
}
