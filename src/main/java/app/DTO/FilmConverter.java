package app.DTO;

import app.entity.Actor;
import app.entity.Category;
import app.entity.Film;
import app.entity.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmConverter implements BaseConverter<Film, FilmDto> {
    private final BaseConverter<Language, LanguageDto> languageConverter;
    private final BaseConverter<Actor, ActorDto> actorConverter;
    private final BaseConverter<Category, CategoryDto> categoryConverter;

    @Autowired
    public FilmConverter(BaseConverter<Language, LanguageDto> languageConverter,
                         BaseConverter<Actor, ActorDto>  actorConverter,
                         BaseConverter<Category, CategoryDto> categoryConverter) {
        this.languageConverter = languageConverter;
        this.actorConverter = actorConverter;
        this.categoryConverter = categoryConverter;
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
        filmDto.setRating(from.getRating());
        filmDto.setLastUpdate(from.getLastUpdate());
        filmDto.setSpecialFeatures(from.getSpecialFeatures());
        filmDto.setFulltext(from.getFulltext());
        filmDto.setActors(actorConverter.convertAll(from.getActors()));
        filmDto.setCategories(categoryConverter.convertAll(from.getCategories()));
        return filmDto;
    }
}
