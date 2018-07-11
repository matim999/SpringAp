package app.DTO.converter;

import app.DTO.responseDTO.ActorDtoNoFilm;
import app.entity.Actor;
import org.springframework.stereotype.Component;

@Component
public class ActorNoFilmConverter implements BaseConverter<Actor, ActorDtoNoFilm> {
    @Override
    public ActorDtoNoFilm convert(Actor from) {
        ActorDtoNoFilm actorDto = new ActorDtoNoFilm();
        actorDto.setActorId(from.getActorId());
        actorDto.setFirstName(from.getFirstName());
        actorDto.setLastName(from.getLastName());
        return actorDto;
    }
}
