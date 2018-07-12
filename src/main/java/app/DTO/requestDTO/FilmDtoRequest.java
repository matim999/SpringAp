package app.DTO.requestDTO;

import app.DTO.responseDTO.ActorDtoNoFilm;
import app.DTO.responseDTO.CategoryDtoNoFilm;
import app.DTO.responseDTO.LanguageDto;
import app.entity.Mpaa_rating;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public @Data
class FilmDtoRequest {
    private String title;
    private String description;
    private int releaseYear;
    private int languageId;
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private String[] specialFeatures;
    private Collection<Integer> actors;
    private Collection<Integer> categories;
}
