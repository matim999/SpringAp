package app.DTO.requestDTO;

import lombok.Data;

import java.util.Collection;

public @Data
class CategoryDtoRequest {
    private String name;
    private Collection<Integer> films;
}
