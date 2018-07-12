package app.DTO.converter;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.ActorDto;
import app.DTO.responseDTO.CategoryDto;
import app.DTO.responseDTO.FilmDtoWithListActorCategory;
import app.DTO.responseDTO.LanguageDto;
import app.entity.Actor;
import app.entity.Category;
import app.entity.Film;
import app.entity.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmWithListActorCategoryConverter implements BaseConverter<Film, FilmDtoWithListActorCategory> {
    private final BaseConverter<Language, LanguageDto> languageConverter;
    private final BaseConverter<Actor, ActorDto> actorConverter;
    private final BaseConverter<Category, CategoryDto> categoryConverter;

    @Autowired
    public FilmWithListActorCategoryConverter(BaseConverter<Language, LanguageDto> languageConverter,
                                              BaseConverter<Actor, ActorDto> actorConverter, BaseConverter<Category, CategoryDto> categoryConverter) {
        this.languageConverter = languageConverter;
        this.actorConverter = actorConverter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public FilmDtoWithListActorCategory convert(Film from) {
        FilmDtoWithListActorCategory filmDto = new FilmDtoWithListActorCategory();
        filmDto.setFilmId(from.getFilmId());
        filmDto.setTitle(from.getTitle());
        filmDto.setDescription(from.getDescription());
        filmDto.setReleaseYear(from.getReleaseYear());
        filmDto.setLanguage(languageConverter.convertAll(from.getLanguage()));
        filmDto.setRentalDuration(from.getRentalDuration());
        filmDto.setRentalRate(from.getRentalRate());
        filmDto.setLength(from.getLength());
        filmDto.setReplacementCost(from.getReplacementCost());
        filmDto.setSpecialFeatures(from.getSpecialFeatures());
        filmDto.setActors(actorConverter.convertAll(from.getActors()));
        filmDto.setCategory(categoryConverter.convertAll(from.getCategories()));
        return filmDto;
    }
}
