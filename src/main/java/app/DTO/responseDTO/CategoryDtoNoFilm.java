package app.DTO.responseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

public @Data
class CategoryDtoNoFilm {
    @EqualsAndHashCode.Exclude private int categoryId;
    private String name;
}
