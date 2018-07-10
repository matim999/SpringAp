package app.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class ActorDto {
    @EqualsAndHashCode.Exclude private int actorId;
    private String firstName;
    private String lastName;
}
