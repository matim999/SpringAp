package app.DTO.responseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
public @Data
class CountryDto {
    @EqualsAndHashCode.Exclude private int countryId;
    private String country;
}
