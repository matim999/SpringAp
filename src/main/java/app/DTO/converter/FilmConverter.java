package app.DTO.converter;

import app.repository.requestDTO.FilmDtoRequest;
import app.DTO.responseDTO.ActorDtoNoFilm;
import app.DTO.responseDTO.CategoryDtoNoFilm;
import app.DTO.responseDTO.FilmDto;
import app.DTO.responseDTO.LanguageDto;
import app.entity.Actor;
import app.entity.Category;
import app.entity.Film;
import app.entity.Language;
import app.finder.ActorFinder;
import app.finder.CategoryFinder;
import app.finder.LanguageFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class FilmConverter implements BaseConverter<Film, FilmDto>, ToBaseConverter<FilmDtoRequest, FilmDto> {
    private final BaseConverter<Language, LanguageDto> languageConverter;
    private final BaseConverter<Actor, ActorDtoNoFilm> actorConverter;
    private final BaseConverter<Category, CategoryDtoNoFilm> categoryConverter;
    private final LanguageFinder languageFinder;
    private final ActorFinder actorFinder;
    private final CategoryFinder categoryFinder;

    @Autowired
    public FilmConverter(BaseConverter<Language, LanguageDto> languageConverter,
                         BaseConverter<Actor, ActorDtoNoFilm> actorConverter,
                         BaseConverter<Category, CategoryDtoNoFilm> categoryConverter, LanguageFinder languageFinder, ActorFinder actorFinder, CategoryFinder categoryFinder) {
        this.languageConverter = languageConverter;
        this.actorConverter = actorConverter;
        this.categoryConverter = categoryConverter;
        this.languageFinder = languageFinder;
        this.actorFinder = actorFinder;
        this.categoryFinder = categoryFinder;
    }

    @Override
    public FilmDto convert(Film from) {
        FilmDto filmDto = new FilmDto();
        filmDto.setFilmId(from.getFilmId());
        filmDto.setTitle(from.getTitle());
        filmDto.setDescription(from.getDescription());
        filmDto.setReleaseYear(from.getReleaseYear());
        filmDto.setLanguage(languageConverter.convertAll(from.getLanguage()));
        filmDto.setRentalDuration(from.getRentalDuration());
        filmDto.setRentalRate(from.getRentalRate());
        filmDto.setLength(from.getLength());
        filmDto.setReplacementCost(from.getReplacementCost());
//        filmDto.setSpecialFeatures(from.getSpecialFeatures());
        filmDto.setActors(actorConverter.convertAll(from.getActors()));
        filmDto.setCategories(categoryConverter.convertAll(from.getCategories()));
        return filmDto;
    }

    @Override
    public FilmDto convertToBase(FilmDtoRequest from) {
        FilmDto filmDto = new FilmDto();
        filmDto.setTitle(from.getTitle());
        filmDto.setDescription(from.getDescription());
        filmDto.setReleaseYear(from.getReleaseYear());
        filmDto.setLanguage(setLanguageById(from.getLanguageId()));
        filmDto.setRentalDuration(from.getRentalDuration());
        filmDto.setRentalRate(from.getRentalRate());
        filmDto.setLength(from.getLength());
        filmDto.setReplacementCost(from.getReplacementCost());
        filmDto.setSpecialFeatures(from.getSpecialFeatures());
        filmDto.setActors(setActorsById(from.getActors()));
        filmDto.setCategories(setCategoriesById(from.getCategories()));
        return filmDto;
    }

    private Collection<CategoryDtoNoFilm> setCategoriesById(Collection<Integer> categories) {
        return categories.stream().map(a -> categoryConverter.convertAll(categoryFinder.findCategoryById(a))).collect(Collectors.toList());
    }

    private Collection<ActorDtoNoFilm> setActorsById(Collection<Integer> actors) {
        return actors.stream().map(a -> actorConverter.convertAll(actorFinder.findActorById(a))).collect(Collectors.toList());
    }

    private LanguageDto setLanguageById(int languageId) {
        return languageConverter.convertAll(languageFinder.findLanguageById(languageId));
    }
}
