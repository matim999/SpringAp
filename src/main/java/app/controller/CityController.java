package app.controller;

import app.DTO.BaseConverter;
import app.DTO.CityConverter;
import app.DTO.CityDto;
import app.DTO.CountryDto;
import app.entity.City;
import app.finder.CityFinder;
import app.entity.Country;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.CityService;

@RestController
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

    @GetMapping(path = "/city")
    public @ResponseBody
    ResponseEntity<java.util.Collection<CityDto>> getAllCity() {
        return new ResponseEntity<java.util.Collection<CityDto>>(cityConverter.convertAll(cityFinder.findAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/city/")
    public @ResponseBody
    ResponseEntity<java.util.Collection<CityDto>> getCityByCityAndCountry(@RequestParam String city, @RequestParam String country) {
        return new ResponseEntity<java.util.Collection<CityDto>>(cityConverter.convertAll(cityFinder.findCityByCityAndCountry(city, country)), HttpStatus.OK);
    }

    @GetMapping(path="city/{id}")
    public @ResponseBody
    ResponseEntity<CityDto> getAllCityById(@PathVariable int id) {
        return new ResponseEntity<>(cityConverter.convertAll(cityFinder.findById(id)), HttpStatus.OK);
    }

    @DeleteMapping(path="city/{id}")
    public @ResponseBody
    ResponseEntity deleteCityById(@PathVariable int id) {
        cityService.deleteCityById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/city/{id}")
    public @ResponseBody
    ResponseEntity updateCity(@RequestBody City city, @PathVariable int id)
    {
        cityService.updateCity(id, city);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity addNewCity(@RequestBody City city) {
        cityService.addNewCity(city);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path="city/{id}/country")
    public @ResponseBody
    ResponseEntity<CountryDto> getCityCountry(@PathVariable int id) {
        return new ResponseEntity<>(countryConverter.convertAll(cityFinder.findCityCountry(id)), HttpStatus.OK);
    }

    @PutMapping(path="city/{id}/country/{countryId}")
    public @ResponseBody
    ResponseEntity getCityCountry(@PathVariable int id, @PathVariable int countryId) {
        cityService.updateCityCountryId(id, countryId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
