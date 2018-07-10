package app.controller;

import app.entity.Actor;
import app.entity.Category;
import app.finder.ActorFinder;
import app.finder.CategoryFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    private final CategoryFinder categoryFinder;

    @Autowired
    public CategoryController(CategoryFinder categoryFinder) {
        this.categoryFinder = categoryFinder;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List> findAllCategory() {
        return new ResponseEntity<>(categoryFinder.findAllCategory(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Category> findCategoryById(@PathVariable int id) {
        return new ResponseEntity<>(categoryFinder.findCategoryById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findCategoryByName(@RequestParam String name) {
        return new ResponseEntity<>(categoryFinder.findAllCategoryByName(name), HttpStatus.OK);
    }
}
