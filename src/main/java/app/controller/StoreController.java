package app.controller;

import app.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import app.repository.RepositoryStore;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final RepositoryStore repo;

    @Autowired
    public StoreController(RepositoryStore repo) {
        this.repo = repo;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    List getAllStores() {
        return repo.findAll();
    }

    @PostMapping(path="/add")
    public @ResponseBody
    String addStore(@RequestBody Store store) {
        repo.save(store);
        return "Done";
    }
}