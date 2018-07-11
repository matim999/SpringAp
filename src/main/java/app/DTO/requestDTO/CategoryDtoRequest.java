package app.DTO.requestDTO;

import app.DTO.responseDTO.FilmDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

public @Data
class CategoryDtoRequest {
    private String name;
    private Collection<Integer> films;
}
