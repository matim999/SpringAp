package app.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public @Data class AddressDto {
    private int addressId;
    private String address;
    private String address2;
    private String district;
    private CityDto city;
    private String postalCode;
    private String phone;
}
