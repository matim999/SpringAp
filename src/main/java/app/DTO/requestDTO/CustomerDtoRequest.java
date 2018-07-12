package app.DTO.requestDTO;

import app.DTO.responseDTO.AddressDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

public @Data
class CustomerDtoRequest {
    private int storeId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer addressId;
    private boolean activebool;
    private Date createDate;
    private int active;
}
