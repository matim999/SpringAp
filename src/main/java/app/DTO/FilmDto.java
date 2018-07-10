package app.DTO;

import app.entity.Actor;
import app.entity.Category;
import app.entity.Language;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public @Data
class FilmDto {
    @EqualsAndHashCode.Exclude private int filmId;
    private String title;
    private String description;
    private int releaseYear;
    private LanguageDto language;
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private String rating;
    private String lastUpdate;
    private String specialFeatures;
    private String fulltext;
    private Collection<ActorDto> actors = new ArrayList<>();
    private Collection<CategoryDto> categories = new ArrayList<>();
}
