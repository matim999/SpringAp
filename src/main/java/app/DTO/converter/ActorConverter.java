package app.DTO.converter;

import app.DTO.requestDTO.ActorDtoRequest;
import app.DTO.responseDTO.FilmDto;
import app.DTO.responseDTO.ActorDto;
import app.entity.Actor;
import app.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ActorConverter implements BaseConverter<Actor, ActorDto>, ToBaseConverter<ActorDtoRequest, ActorDto> {
    private final BaseConverter<Film, FilmDto> filmConverter;

    @Autowired
    public ActorConverter(BaseConverter<Film, FilmDto> filmConverter) {
        this.filmConverter = filmConverter;
    }
    @Override
    public ActorDto convert(Actor from) {
        ActorDto actorDto = new ActorDto();
        actorDto.setActorId(from.getActorId());
        actorDto.setFirstName(from.getFirstName());
        actorDto.setLastName(from.getLastName());
        actorDto.setFilms(filmConverter.convertAll(from.getFilms()));
        return actorDto;
    }

    public ActorDto convertToBase(ActorDtoRequest from) {
        ActorDto actorDto = new ActorDto();
        actorDto.setFirstName(from.getFirstName());
        actorDto.setLastName(from.getLastName());
        actorDto.setFilms(new ArrayList<>());
        return actorDto;
    }
}
