package app.entity;

import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_film_id_seq")
    @SequenceGenerator(
            name="film_film_id_seq",
            sequenceName="film_film_id_seq",
            allocationSize = 1
    )
    @Column(name = "film_id")
    private @Getter int filmId;
    private @Getter String title;
    private @Getter String description;
    private @Getter int releaseYear;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "language_id")
    private @Getter Language language;
    private @Getter int rentalDuration;
    private @Getter double rentalRate;
    private @Getter int length;
    private @Getter double replacementCost;
    private @Getter String rating;
    private @Getter String lastUpdate;
    private @Getter String specialFeatures;
    private @Getter String fulltext;
    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private @Getter List<Actor> actors = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private @Getter List<Category> categories = new ArrayList<>();
}
