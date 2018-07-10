package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Actor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_actor_id_seq")
    @SequenceGenerator(
            name="actor_actor_id_seq",
            sequenceName="actor_actor_id_seq",
            allocationSize = 1
    )
    @Column(name = "actor_id")
    private @Getter int actorId;
    private @Getter String firstName;
    private @Getter String lastName;
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private @Getter List<Film> films = new ArrayList<>();
    @JsonIgnore
    public List<Film> getFilms() {
        return films;
    }
    public void addFilm(Film film) {
        films.add(film);
    }

}
