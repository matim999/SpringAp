package app.DTO.requestDTO;

import app.DTO.responseDTO.FilmDto;
import lombok.Data;

import java.util.Collection;
import java.util.List;

public @Data
class ActorDtoRequest {
    private String firstName;
    private String lastName;
}
