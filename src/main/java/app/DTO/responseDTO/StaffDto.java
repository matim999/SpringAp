package app.DTO.responseDTO;

import app.DTO.responseDTO.AddressDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class StaffDto {
    @EqualsAndHashCode.Exclude private int staffId;
    private String firstName;
    private String lastName;
    private AddressDto address;
    private String email;
    private boolean active;
    private String username;
    private String password;
    private int storeId;
}
