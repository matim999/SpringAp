package app.DTO.responseDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class StaffDtoNoRole {
    @EqualsAndHashCode.Exclude
    private int staffId;
    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private AddressDto address;
    private String email;
    private boolean active;
    private String username;
    private String password;
    private int storeId;
}
