package app.controller;

import app.entity.Category;
import app.entity.City;
import app.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/test")
public class TestController {
    private final CityRepository cityRepository;

    @Autowired
    public TestController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

//    @GetMapping
//    public Boolean test()
//    {
//        Collection<City> collection = cityRepository.findAllById(99999).
//        return city1.equals(city2);
//    }
}
