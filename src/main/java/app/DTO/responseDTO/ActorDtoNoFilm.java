package app.DTO.responseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class ActorDtoNoFilm {
    @EqualsAndHashCode.Exclude private int actorId;
    private String firstName;
    private String lastName;
}
