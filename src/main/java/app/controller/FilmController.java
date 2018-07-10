package app.controller;

import app.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.OutputKeys;
import java.util.List;

@RestController
@RequestMapping(path = "/film")
public class FilmController {
    private final FilmRepository filmRepository;

    @Autowired
    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllFilm()
    {
        return new ResponseEntity<>(filmRepository.findAll(), HttpStatus.OK);
    }
}
