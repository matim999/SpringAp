package app.entity;

import app.DTO.responseDTO.FilmDto;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@EqualsAndHashCode
@TypeDefs({
        @TypeDef(
                name = "pgsql_enum",
                typeClass = PostgreSQLEnumType.class
        ),
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        )
})
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_film_id_seq")
    @SequenceGenerator(
            name = "film_film_id_seq",
            sequenceName = "film_film_id_seq",
            allocationSize = 1
    )
    @Column(name = "film_id")
    @EqualsAndHashCode.Exclude
    private @Getter
    int filmId;
    private @Getter
    String title;
    private @Getter
    String description;
    private @Getter
    int releaseYear;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "language_id")
    private @Getter
    @Setter
    Language language;
    private @Getter
    int rentalDuration;
    private @Getter
    double rentalRate;
    private @Getter
    int length;
    private @Getter
    double replacementCost;
    //    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "mpaa_rating")
//    @Type( type = "pgsql_enum" )
//    @Convert(converter = MpaaRatingConverter.class)
//    private @Getter Mpaa_rating rating;
//    @Column(columnDefinition = "text[]")
//    @Type(type = "string-array")
//    private @Getter
//    String[] specialFeatures;
    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @EqualsAndHashCode.Exclude
    private @Getter
    List<Actor> actors;
    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @EqualsAndHashCode.Exclude
    private @Getter
    List<Category> categories;

    public Film(FilmDto filmDto) {
        this.title = filmDto.getTitle();
        this.description = filmDto.getDescription();
        this.releaseYear = filmDto.getReleaseYear();
        this.language = null;
        this.rentalDuration = filmDto.getRentalDuration();
        this.rentalRate = filmDto.getRentalRate();
        this.length = filmDto.getLength();
        this.replacementCost = filmDto.getReplacementCost();
//        this.specialFeatures = filmDto.getSpecialFeatures();
        this.actors = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public Film() {
        super();
    }

    public void update(FilmDto filmDto) {
        this.title = filmDto.getTitle();
        this.description = filmDto.getDescription();
        this.releaseYear = filmDto.getReleaseYear();
        this.rentalDuration = filmDto.getRentalDuration();
        this.rentalRate = filmDto.getRentalRate();
        this.length = filmDto.getLength();
        this.replacementCost = filmDto.getReplacementCost();
//        this.specialFeatures = filmDto.getSpecialFeatures();
    }

    public void addActor(Collection<Actor> actors) {
        this.actors.addAll(actors);
    }

    public void addCategory(Collection<Category> categories) {
        this.categories.addAll(categories);
    }
}
