package app.DTO.responseDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;

public @Data
class FilmDto {
    @EqualsAndHashCode.Exclude
    private int filmId;
    private String title;
    @JsonIgnore
    private String description;
    @JsonIgnore
    private int releaseYear;
    @JsonIgnore
    private LanguageDto language;
    private int rentalDuration;
    private double rentalRate;
    @JsonIgnore
    private int length;
    @JsonIgnore
    private double replacementCost;
    @JsonIgnore
    private String[] specialFeatures;
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Collection<ActorDtoNoFilm> actors = new ArrayList<>();
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Collection<CategoryDtoNoFilm> categories = new ArrayList<>();
}
