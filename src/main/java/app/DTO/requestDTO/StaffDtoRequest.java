package app.DTO.requestDTO;

import app.DTO.responseDTO.AddressDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class StaffDtoRequest {
    private String firstName;
    private String lastName;
    private int addressId;
    private String email;
    private boolean active;
    private String username;
    private String password;
    private int storeId;
}
