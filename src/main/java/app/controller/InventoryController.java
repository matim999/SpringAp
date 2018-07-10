package app.controller;

import app.entity.Film;
import app.entity.Inventory;
import app.finder.FilmFinder;
import app.finder.InventoryFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryController {
    private final InventoryFinder inventoryFinder;

    @Autowired
    public InventoryController(InventoryFinder inventoryFinder) {
        this.inventoryFinder = inventoryFinder;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllInventory()
    {
        return new ResponseEntity<>(inventoryFinder.findAllInventory(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Inventory> findInventoryById(@PathVariable int id) {
        return new ResponseEntity<>(inventoryFinder.findInventoryById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllInventoryByFilmTitle(@RequestParam String title) {
        return new ResponseEntity<>(inventoryFinder.findAllInventoryByFilmTitle(title), HttpStatus.OK);
    }
}
