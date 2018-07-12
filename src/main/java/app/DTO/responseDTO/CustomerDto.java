package app.DTO.responseDTO;

import app.DTO.responseDTO.AddressDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

public @Data
class CustomerDto {
    @EqualsAndHashCode.Exclude
    private int customerId;
    @EqualsAndHashCode.Exclude private int storeId;
    @JsonIgnore private String firstName;
    @JsonIgnore private String lastName;
    @JsonIgnore private String email;
    @JsonIgnore private AddressDto address;
    @JsonIgnore private boolean activebool;
    @EqualsAndHashCode.Exclude @JsonIgnore private Date createDate;
    @JsonIgnore private int active;
}
