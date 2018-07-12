package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.requestDTO.FilmDtoRequest;
import app.DTO.responseDTO.FilmDto;
import app.entity.Film;
import app.entity.Mpaa_rating;
import app.finder.FilmFinder;
import app.repository.FilmRepository;
import app.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/film")
public class FilmController {
    private final FilmRepository filmRepository;
    private final FilmFinder filmFinder;
    private final FilmService filmService;
    private final BaseConverter<Film, FilmDto> filmConverter;

    @Autowired
    public FilmController(FilmRepository filmRepository, FilmFinder filmFinder, FilmService filmService, BaseConverter<Film, FilmDto> filmConverter) {
        this.filmRepository = filmRepository;
        this.filmFinder = filmFinder;
        this.filmService = filmService;
        this.filmConverter = filmConverter;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllFilm()
    {
        return new ResponseEntity(filmConverter.convertAll(filmFinder.findAllFilm()), HttpStatus.OK);
    }

    @PostMapping
    private @ResponseBody
    ResponseEntity<List> findAllFilm(@RequestBody FilmDtoRequest filmDtoRequest)
    {
        filmService.addNewFilm(filmDtoRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Film> findFilmById(@PathVariable int id) {
        return new ResponseEntity(filmConverter.convertAll(filmFinder.findFilmById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllFilmByTitle(@RequestParam String title) {
        return new ResponseEntity(filmConverter.convertAll(filmFinder.findAllFilmByTitle(title)), HttpStatus.OK);
    }
}
