package controller;

import app.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private final RepositoryStore repo;

    public StoreController(RepositoryStore repo) {
        this.repo = repo;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    List getAllStores() {
        return repo.findAll();
    }

    @GetMapping
    public @ResponseBody
    Optional<Store> getStore(@RequestParam int store_id){
        return repo.findById(store_id);
    }
}
