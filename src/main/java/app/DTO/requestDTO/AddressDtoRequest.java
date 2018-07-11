package app.DTO.requestDTO;

import app.DTO.responseDTO.CityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Component
public @Data
class AddressDtoRequest {
    private String address;
    private String address2;
    private String district;
    private int cityId;
    private String postalCode;
    private String phone;
}
