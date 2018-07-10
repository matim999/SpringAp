package app.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
public @Data
class CountryDto {
    private int countryId;
    private String country;
}
