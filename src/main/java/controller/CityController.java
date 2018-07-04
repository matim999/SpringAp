package controller;

import app.City;
import app.Country;
import app.RepositoryCity;
import app.RepositoryCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private RepositoryCity repo;

    @GetMapping(path="/all")
    public @ResponseBody
    List getAllCity() {
        return repo.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewCity(@RequestBody City city)
    {
        repo.save(city);
        return "Saved";
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody
    String deleteCity(@RequestParam int cityId)
    {
        City city = repo.findById(cityId).get();
        repo.delete(city);
        return "Deleted";
    }
}
