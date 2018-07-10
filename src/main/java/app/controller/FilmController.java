package app.controller;

import app.entity.Customer;
import app.entity.Film;
import app.finder.FilmFinder;
import app.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;
import java.util.List;

@RestController
@RequestMapping(path = "/film")
public class FilmController {
    private final FilmFinder filmFinder;

    @Autowired
    public FilmController(FilmFinder filmFinder) {
        this.filmFinder = filmFinder;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllFilm()
    {
        return new ResponseEntity<>(filmFinder.findAllFilm(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Film> findFilmById(@PathVariable int id) {
        return new ResponseEntity<>(filmFinder.findFilmById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllFilmByTitle(@RequestParam String name) {
        return new ResponseEntity<>(filmFinder.findAllFilmByTitle(name), HttpStatus.OK);
    }
}
