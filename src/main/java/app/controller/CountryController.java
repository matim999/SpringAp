package app.controller;

import app.entity.Country;
import app.finder.CountryFinder;
import app.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryFinder countryFinder;
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryFinder countryFinder, CountryService countryService) {
        this.countryFinder = countryFinder;
        this.countryService = countryService;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity getAllCountry() {
        return new ResponseEntity(countryFinder.findAllCountry(), HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity addNewCountry(@RequestBody Country country)
    {
        return countryService.addNewCountry(country);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getAllCountry(@PathVariable int id) {
        return new ResponseEntity(countryFinder.findCountryById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity deleteCountryById(@PathVariable int id)
    {
        return new ResponseEntity(countryService.deleteCountryById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity getAllCountry(@RequestParam String country) {
        return new ResponseEntity(countryFinder.findCountryByCountry(country), HttpStatus.OK);
    }
}
