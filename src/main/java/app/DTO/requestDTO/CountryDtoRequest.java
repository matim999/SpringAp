package app.DTO.requestDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Component
public @Data
class CountryDtoRequest {
    private String country;
}
