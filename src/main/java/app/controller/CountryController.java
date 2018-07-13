package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.CountryDto;
import app.entity.Country;
import app.finder.CountryFinder;
import app.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final BaseConverter<Country, CountryDto> countryConverter;
    private final CountryFinder countryFinder;
    private final CountryService countryService;

    @Autowired
    public CountryController(BaseConverter<Country, CountryDto> countryConverter, CountryFinder countryFinder, CountryService countryService) {
        this.countryConverter = countryConverter;
        this.countryFinder = countryFinder;
        this.countryService = countryService;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<java.util.Collection<CountryDto>> getAllCountry() {
        return new ResponseEntity<java.util.Collection<CountryDto>>(countryConverter.convertAll(countryFinder.findAllCountry()), HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity addNewCountry(@RequestBody Country country) {
        countryService.addNewCountry(country);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<CountryDto> getAllCountry(@PathVariable int id) {
        //CountryDto list = countryConverter.convertAll(countryFinder.findCountryById(id));
        return new ResponseEntity<>(countryConverter.convertAll(countryFinder.findCountryById(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity deleteCountryById(@PathVariable int id) {
        countryService.deleteCountryById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<CountryDto> getAllCountry(@RequestParam String country) {
        return new ResponseEntity<>(countryConverter.convertAll(countryFinder.findCountryByCountry(country)), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity updateCountryById(@RequestBody Country country, @PathVariable int id) {
        countryService.updateCountryByID(id, country);
        return new ResponseEntity(HttpStatus.OK);
    }
}
