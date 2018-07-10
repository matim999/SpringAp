package app.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public @Data
class CityDto {
    private int cityId;
    private String city;
    private CountryDto country;
}
