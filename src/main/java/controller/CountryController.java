package controller;

import app.Country;
import app.RepositoryCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private RepositoryCountry repo;

    @GetMapping(path="/all")
    public @ResponseBody
    List getAllCountry() {
        return repo.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewCountry(@RequestBody Country country)
    {
        repo.save(country);
        return "Saved";
    }

}
