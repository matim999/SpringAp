package controller;

import app.Country;
import app.RepositoryCity;
import app.RepositoryCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private final RepositoryCountry repo;
    @Autowired
    private final RepositoryCity repoCity;

    public CountryController(RepositoryCountry repo, RepositoryCity repoCity) {
        this.repo = repo;
        this.repoCity = repoCity;
    }

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

    @DeleteMapping("/delete")
    public @ResponseBody
    String deleteCountry()
    {
        repo.deleteById(1000);
        return "Deleted";
    }

}
