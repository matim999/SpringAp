package app.controller;

import app.entity.Inventory;
import app.entity.Language;
import app.finder.InventoryFinder;
import app.finder.LanguageFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/language")
public class LanguageController {
    private final LanguageFinder languageFinder;

    @Autowired
    public LanguageController(LanguageFinder inventoryFinder) {
        this.languageFinder = inventoryFinder;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllLanguage()
    {
        return new ResponseEntity<>(languageFinder.findAllLanguage(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Language> findLanguageById(@PathVariable int id) {
        return new ResponseEntity<>(languageFinder.findLanguageById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllLanguageByName(@RequestParam String name) {
        return new ResponseEntity<>(languageFinder.findAllLanguageByName(name), HttpStatus.OK);
    }
}
