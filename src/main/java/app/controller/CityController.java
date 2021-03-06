package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.CityDto;
import app.DTO.responseDTO.CountryDto;
import app.entity.City;
import app.entity.Country;
import app.finder.CityFinder;
import app.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/city")
public class CityController {
    private final CityService cityService;
    private final CityFinder cityFinder;
    private final BaseConverter<City, CityDto> cityConverter;
    private final BaseConverter<Country, CountryDto> countryConverter;

    @Autowired
    public CityController(CityService cityService, CityFinder cityFinder, BaseConverter<City, CityDto> cityConverter, BaseConverter<Country, CountryDto> countryConverter) {
        this.cityService = cityService;
        this.cityFinder = cityFinder;
        this.cityConverter = cityConverter;
        this.countryConverter = countryConverter;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<java.util.Collection<CityDto>> getAllCity() {
        return new ResponseEntity<java.util.Collection<CityDto>>(cityConverter.convertAll(cityFinder.findAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<java.util.Collection<CityDto>> getCityByCityAndCountry(@RequestParam String city, @RequestParam String country) {
        return new ResponseEntity<java.util.Collection<CityDto>>(cityConverter.convertAll(cityFinder.findCityByCityAndCountry(city, country)), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<CityDto> getAllCityById(@PathVariable int id) {
        return new ResponseEntity<>(cityConverter.convertAll(cityFinder.findById(id)), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity deleteCityById(@PathVariable int id) {
        cityService.deleteCityById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity updateCity(@RequestBody City city, @PathVariable int id) {
        cityService.updateCity(id, city);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/")
    public @ResponseBody
    ResponseEntity test(@RequestBody CityDto city) {
        return new ResponseEntity(cityService.checkForCity(city), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public @ResponseBody
    ResponseEntity addNewCity(@RequestBody City city) {
        cityService.addNewCity(city);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity addCity(@RequestBody City city) {
        cityService.addCity(city);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/country")
    public @ResponseBody
    ResponseEntity<CountryDto> getCityCountry(@PathVariable int id) {
        return new ResponseEntity<>(countryConverter.convertAll(cityFinder.findCityCountry(id)), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/country/{countryId}")
    public @ResponseBody
    ResponseEntity getCityCountry(@PathVariable int id, @PathVariable int countryId) {
        cityService.updateCityCountryId(id, countryId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
