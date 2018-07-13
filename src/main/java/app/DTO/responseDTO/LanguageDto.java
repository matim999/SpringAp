package app.DTO.responseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class LanguageDto {
    @EqualsAndHashCode.Exclude
    private int languageId;
    private String name;
}
