package app.service;

import app.DTO.ErrorCode;
import app.DTO.converter.BaseConverter;
import app.DTO.converter.ToBaseConverter;
import app.DTO.requestDTO.ActorDtoRequest;
import app.DTO.requestDTO.FilmDtoRequest;
import app.DTO.responseDTO.ActorDto;
import app.DTO.responseDTO.FilmDto;
import app.entity.Actor;
import app.entity.Film;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import app.repository.ActorRepository;
import app.repository.CategoryRepository;
import app.repository.FilmRepository;
import app.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;
    private final ActorRepository actorRepository;
    private final CategoryRepository categoryRepository;
    private final BaseConverter<Film, FilmDto> filmConverter;
    private final ToBaseConverter<FilmDtoRequest, FilmDto> filmRequestConverter;

    @Autowired
    public FilmService(FilmRepository filmRepository, LanguageRepository languageRepository, ActorRepository actorRepository, CategoryRepository categoryRepository, BaseConverter<Film, FilmDto> filmConverter, ToBaseConverter<FilmDtoRequest, FilmDto> filmRequestConverter) {
        this.filmRepository = filmRepository;
        this.languageRepository = languageRepository;
        this.actorRepository = actorRepository;
        this.categoryRepository = categoryRepository;
        this.filmConverter = filmConverter;
        this.filmRequestConverter = filmRequestConverter;
    }

    public void addNewFilm(FilmDtoRequest filmDtoRequest)
    {
        Collection<Film> collection = filmRepository.findAllByTitle(filmDtoRequest.getTitle()).orElse(new ArrayList());
        if (collection.isEmpty()){
            addFilm(filmDtoRequest);
            return;
        }
        FilmDto filmDto = filmRequestConverter.convertAllToBase(filmDtoRequest);
        Collection<FilmDto> film = filmConverter.convertAll(collection);
        film.forEach(a -> {
            if (a.equals(filmDto))
                throw new ConflictException("Film already exists", ErrorCode.DIFFERENT);
        });
        addFilm(filmDtoRequest);
    }

    private void addFilm(FilmDtoRequest filmDtoRequest) {
        FilmDto filmDto = filmRequestConverter.convertAllToBase(filmDtoRequest);
        Film film = new Film(filmDto);
        film.setLanguage(languageRepository.findById(filmDtoRequest.getLanguageId()).orElseThrow(() -> new MyNotFoundException("Language with ID not found", ErrorCode.DIFFERENT)));
        film.setActors(filmDtoRequest.getActors().stream()
                .map(id -> actorRepository.findById(id)
                        .orElseThrow(() -> new MyNotFoundException("Actor with ID not found", ErrorCode.DIFFERENT)))
                .collect(Collectors.toList()));
        film.setCategories(filmDtoRequest.getCategories().stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new MyNotFoundException("Category with ID not found", ErrorCode.DIFFERENT)))
                .collect(Collectors.toList()));
        filmRepository.save(film);
    }
}
