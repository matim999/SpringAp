package app.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class CategoryDto {
    @EqualsAndHashCode.Exclude private int categoryId;
    private String name;
}
