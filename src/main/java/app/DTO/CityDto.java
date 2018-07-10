package app.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public @Data
class CityDto {
    @EqualsAndHashCode.Exclude private int cityId;
    private String city;
    private CountryDto country;
}
