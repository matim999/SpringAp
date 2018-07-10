package app.controller;

import app.entity.Language;
import app.entity.Rental;
import app.finder.LanguageFinder;
import app.finder.RentalFinder;
import app.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rental")
@EntityScan("app")
@ComponentScan("app")
public class RentalController {
    private final RentalFinder rentalFinder;

    @Autowired
    public RentalController(RentalFinder rentalFinder) {
        this.rentalFinder = rentalFinder;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllRental()
    {
        return new ResponseEntity<>(rentalFinder.findAllRental(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Rental> findRentalById(@PathVariable int id) {
        return new ResponseEntity<>(rentalFinder.findRentalById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllRentalByRentalDate(@RequestParam String date) {
        return new ResponseEntity<>(rentalFinder.findAllRentalByRentalDate(date), HttpStatus.OK);
    }
}
