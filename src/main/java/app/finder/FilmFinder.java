package app.finder;

import app.entity.Customer;
import app.entity.Film;
import app.exceptions.MyNotFoundException;
import app.repository.CustomerRepository;
import app.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.DTO.ErrorCode.DIFFERENT;

@Component
public class FilmFinder {
    private final FilmRepository filmRepository;

    @Autowired
    public FilmFinder(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> findAllFilm()
    {
        return filmRepository.findAll();
    }

    public Film findFilmById(int id){
        return filmRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Category with given Id not found", DIFFERENT));
    }

    public List<Film> findAllFilmByTitle(String title){
        return filmRepository.findAllByTitle(title).orElseThrow(() -> new MyNotFoundException("Category with given name not found", DIFFERENT));
    }
}
