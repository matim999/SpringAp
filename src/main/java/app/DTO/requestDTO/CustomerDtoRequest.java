package app.DTO.requestDTO;

import app.DTO.responseDTO.AddressDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class CustomerDtoRequest {
    private int storeId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer addressId;
    private boolean activebool;
    private String createDate;
    private int active;
}
