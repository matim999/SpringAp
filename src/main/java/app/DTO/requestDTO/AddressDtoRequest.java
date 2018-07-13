package app.DTO.requestDTO;

import lombok.Data;
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
