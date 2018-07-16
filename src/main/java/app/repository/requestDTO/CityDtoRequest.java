package app.repository.requestDTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data
class CityDtoRequest {
    private String city;
    private int countryId;
}
