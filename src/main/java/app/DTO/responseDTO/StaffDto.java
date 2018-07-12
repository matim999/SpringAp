package app.DTO.responseDTO;

import app.DTO.responseDTO.AddressDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class StaffDto {
    @EqualsAndHashCode.Exclude
    private int staffId;
    @JsonIgnore private String firstName;
    @JsonIgnore private String lastName;
    @EqualsAndHashCode.Exclude @JsonIgnore private AddressDto address;
    @JsonIgnore private String email;
    @EqualsAndHashCode.Exclude @JsonIgnore private boolean active;
    @JsonIgnore private String username;
    @JsonIgnore private String password;
    private int storeId;
}
