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
    public CityController(CityService cityService, CityFinder cityFinder, CityConverter cityConverter, BaseConverter<Country, CountryDto> countryConverter) {
        this.cityService = cityService;
        this.cityFinder = cityFinder;
        this.cityConverter = cityConverter;
        this.countryConverter = countryConverter;
    }

    @GetMapping(path = "/city")
    public @ResponseBody
    ResponseEntity getAllCity() {
        return new ResponseEntity(cityConverter.convertAll(cityFinder.findAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/city/")
    public @ResponseBody
    ResponseEntity getCityByCityAndCountry(@RequestParam String city, @RequestParam String country) {
        return new ResponseEntity(cityConverter.convertAll(cityFinder.findCityByCityAndCountry(city, country)), HttpStatus.OK);
    }

    @GetMapping(path="city/{id}")
    public @ResponseBody
    ResponseEntity getAllCityById(@PathVariable int id) {
        return new ResponseEntity(cityConverter.convertAll(cityFinder.findById(id)), HttpStatus.OK);
    }

    @DeleteMapping(path="city/{id}")
    public @ResponseBody
    ResponseEntity deleteCityById(@PathVariable int id) {
        return new ResponseEntity(cityService.deleteCityById(id));
    }

    @PutMapping(path = "/city/{id}")
    public @ResponseBody
    ResponseEntity updateCity(@RequestBody City city, @PathVariable int id)
    {
        return new ResponseEntity(cityService.updateCity(id, city));
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity addNewCity(@RequestBody City city)
    {
        return new ResponseEntity(cityService.addNewCity(city));
    }

    @GetMapping(path="city/{id}/country")
    public @ResponseBody
    ResponseEntity getCityCountry(@PathVariable int id) {
        return new ResponseEntity(countryConverter.convertAll(cityFinder.findCityCountry(id)), HttpStatus.OK);
    }

    @PutMapping(path="city/{id}/country/{countryId}")
    public @ResponseBody
    ResponseEntity getCityCountry(@PathVariable int id, @PathVariable int countryId) {
        return new ResponseEntity(cityService.updateCityCountryId(id, countryId));
    }
}
