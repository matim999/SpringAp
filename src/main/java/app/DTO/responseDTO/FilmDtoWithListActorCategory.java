package app.DTO.responseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

public @Data
class FilmDtoWithListActorCategory {
    @EqualsAndHashCode.Exclude
    private int filmId;
    private String title;
    private String description;
    private int releaseYear;
    private LanguageDto language;
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private String[] specialFeatures;
    private Collection<ActorDto> actors;
    private Collection<CategoryDto> category;
}
