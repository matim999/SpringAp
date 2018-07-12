package app.DTO.responseDTO;

import app.DTO.responseDTO.AddressDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

public @Data
class CustomerDto {
    @EqualsAndHashCode.Exclude private int customerId;
    @EqualsAndHashCode.Exclude private int storeId;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDto address;
    private boolean activebool;
    @EqualsAndHashCode.Exclude private Date createDate;
    private int active;
}
