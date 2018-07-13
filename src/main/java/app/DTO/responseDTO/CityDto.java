package app.DTO.responseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Component
public @Data
class CityDto {
    @EqualsAndHashCode.Exclude
    private int cityId;
    private String city;
    private CountryDto country;
}
