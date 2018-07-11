package app.DTO.responseDTO;

import app.entity.Mpaa_rating;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public @Data
class FilmDtoWithListActorCategory {
    @EqualsAndHashCode.Exclude private int filmId;
    private String title;
    private String description;
    private int releaseYear;
    private LanguageDto language;
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private Mpaa_rating rating;
    private String[] specialFeatures;
    private Collection<ActorDto> actors;
    private Collection<CategoryDto> category;
}
