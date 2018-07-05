package controller;

import app.City;
import app.Country;
import app.RepositoryCity;
import app.RepositoryCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private RepositoryCity repo;
    EntityManager entityManager;

    @GetMapping(path="/all")
    public @ResponseBody
    List getAllCity() {
        return repo.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewCity(@RequestBody City city)
    {
        entityManager.merge(city);
        repo.save(city);
        return "Saved";
    }
}
