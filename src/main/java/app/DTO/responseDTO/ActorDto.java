package app.DTO.responseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

public @Data
class ActorDto {
    @EqualsAndHashCode.Exclude private int actorId;
    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Exclude private Collection<FilmDto> films;
}
