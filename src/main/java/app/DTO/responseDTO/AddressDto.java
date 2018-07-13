package app.DTO.responseDTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data
class AddressDto {
    private int addressId;
    private String address;
    private String address2;
    private String district;
    private CityDto city;
    private String postalCode;
    private String phone;
}
