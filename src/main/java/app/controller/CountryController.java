package app.controller;

import app.DTO.BaseConverter;
import app.DTO.CountryDto;
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
    ResponseEntity<List> getAllCountry() {
        return new ResponseEntity(countryConverter.convertAll(countryFinder.findAllCountry()), HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity addNewCountry(@RequestBody Country country)
    {
        return new ResponseEntity(countryService.addNewCountry(country));
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getAllCountry(@PathVariable int id) {
        CountryDto list = countryConverter.convertAll(countryFinder.findCountryById(id));
        return new ResponseEntity(list, HttpStatus.OK);
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
        return new ResponseEntity(countryConverter.convertAll(countryFinder.findCountryByCountry(country)), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity updateCountryById(@RequestBody Country country, @PathVariable int id){
        return new ResponseEntity(countryService.updateCountryByID(id, country));
    }
}
