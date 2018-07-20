package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.CategoryDto;
import app.entity.Category;
import app.finder.CategoryFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    private final CategoryFinder categoryFinder;
    private final BaseConverter<Category, CategoryDto> categoryConverter;

    @Autowired
    public CategoryController(CategoryFinder categoryFinder, BaseConverter<Category, CategoryDto> categoryConverter) {
        this.categoryFinder = categoryFinder;
        this.categoryConverter = categoryConverter;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List> findAllCategory() {
        return new ResponseEntity(categoryConverter.convertAll(categoryFinder.findAllCategory()), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<CategoryDto> findCategoryById(@PathVariable int id) {
        return new ResponseEntity(categoryConverter.convertAll(categoryFinder.findCategoryById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findCategoryByName(@RequestParam String name) {
        return new ResponseEntity(categoryConverter.convertAll(categoryFinder.findAllCategoryByName(name)), HttpStatus.OK);
    }
}
