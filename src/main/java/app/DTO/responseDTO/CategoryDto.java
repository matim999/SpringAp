package app.DTO.responseDTO;

import lombok.Data;

import java.util.Collection;

public @Data
class CategoryDto {
    private int categoryId;
    private String name;
    private Collection<FilmDto> films;
}
