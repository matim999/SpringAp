package app.DTO;

import app.entity.Address;
import app.entity.Store;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class CustomerDto {
    private int customerId;
    @EqualsAndHashCode.Exclude private int storeId;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDto address;
    private boolean activebool;
    private String createDate;
    private int active;
}
