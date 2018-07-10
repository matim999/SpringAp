package app.DTO;

import app.entity.Actor;
import app.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActorConverter implements BaseConverter<Actor, ActorDto> {
    @Override
    public ActorDto convert(Actor from) {
        ActorDto actorDto = new ActorDto();
        actorDto.setActorId(from.getActorId());
        actorDto.setFirstName(from.getFirstName());
        actorDto.setLastName(from.getLastName());
        return actorDto;
    }
}
