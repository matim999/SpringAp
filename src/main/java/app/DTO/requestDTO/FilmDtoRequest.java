package app.DTO.requestDTO;

import lombok.Data;

import java.util.Collection;

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
    private Collection<Integer> actors;
    private Collection<Integer> categories;
}
