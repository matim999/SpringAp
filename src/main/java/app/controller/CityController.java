package app.controller;

import app.entity.City;
import app.finder.CityFinder;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;
    private final CityFinder cityFinder;

    @Autowired
    public CityController(CityService cityService, CityFinder cityFinder) {
        this.cityService = cityService;
        this.cityFinder = cityFinder;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity getAllCity() {
        return new ResponseEntity(cityFinder.findAll(), HttpStatus.FOUND);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity getCityByCityAndCountry(@RequestParam String city, @RequestParam String country) {
        return new ResponseEntity(cityFinder.findCityByCityAndCountry(city, country), HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public @ResponseBody
    ResponseEntity getAllCityById(@PathVariable int id) {
        return new ResponseEntity(cityFinder.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody
    ResponseEntity deleteCityById(@PathVariable int id) {
        return cityService.deleteCityById(id);
    }

    @PutMapping
    public @ResponseBody
    ResponseEntity updateCity(@RequestBody City city)
    {
        return cityService.updateCity(city);
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity addNewCity(@RequestBody City city)
    {
        return cityService.addNewCity(city);
    }

    @GetMapping(path="/{id}/country")
    public @ResponseBody
    ResponseEntity getCityCoutnry(@PathVariable int id) {
        return cityFinder.findCountry(id);
    }
}
