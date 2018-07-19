package app.entity;

import app.DTO.requestDTO.ActorDtoRequest;
import app.DTO.responseDTO.ActorDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode
public class Actor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_actor_id_seq")
    @SequenceGenerator(
            name = "actor_actor_id_seq",
            sequenceName = "actor_actor_id_seq",
            allocationSize = 1
    )
    @Column(name = "actor_id")
    @EqualsAndHashCode.Exclude
    private @Getter
    int actorId;
    private @Getter
    String firstName;
    private @Getter
    String lastName;
    @ManyToMany(mappedBy = "actors")
    @EqualsAndHashCode.Exclude
    private @Getter
    List<Film> films = new ArrayList<>();

    public Actor(ActorDto actorDto) {
        this.firstName = actorDto.getFirstName();
        this.lastName = actorDto.getLastName();
    }

    public Actor() {
        super();
    }

    public List<Film> getFilms() {
        return films;
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    public void update(ActorDtoRequest actorDtoRequest) {
        this.firstName = actorDtoRequest.getFirstName();
        this.lastName = actorDtoRequest.getLastName();
    }
}
