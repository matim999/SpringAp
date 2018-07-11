package app.entity;

import app.DTO.converter.BaseConverter;
import app.DTO.converter.LanguageConverter;
import app.DTO.responseDTO.FilmDto;
import app.DTO.responseDTO.LanguageDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@EqualsAndHashCode
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_film_id_seq")
    @SequenceGenerator(
            name="film_film_id_seq",
            sequenceName="film_film_id_seq",
            allocationSize = 1
    )
    @Column(name = "film_id")
    @EqualsAndHashCode.Exclude private @Getter int filmId;
    private @Getter String title;
    private @Getter String description;
    private @Getter int releaseYear;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "language_id")
    private @Getter @Setter Language language;
    private @Getter int rentalDuration;
    private @Getter double rentalRate;
    private @Getter int length;
    private @Getter double replacementCost;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "mpaa_rating")
    @Type( type = "pgsql_enum" )
    private @Getter Mpaa_rating rating;
    private @Getter String specialFeatures;
    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @EqualsAndHashCode.Exclude private @Getter @Setter
    List<Actor> actors;
    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @EqualsAndHashCode.Exclude private @Getter @Setter
    List<Category> categories;

    public Film(FilmDto filmDto) {
        this.description = filmDto.getDescription();
        this.releaseYear = filmDto.getReleaseYear();
        this.language = null;
        this.rentalDuration = filmDto.getRentalDuration();
        this.rentalRate = filmDto.getRentalRate();
        this.length = filmDto.getLength();
        this.replacementCost = filmDto.getReplacementCost();
        this.rating = filmDto.getRating();
        this.specialFeatures = filmDto.getSpecialFeatures();
        this.actors = null;
        this.categories = null;
    }

    public Film() {
        super();
    }
}
