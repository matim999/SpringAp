package app.DTO.requestDTO;

import app.DTO.responseDTO.CountryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Component
public @Data
class CityDtoRequest {
    private String city;
    private int countryId;
}
