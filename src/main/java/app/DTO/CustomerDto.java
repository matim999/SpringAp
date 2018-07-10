package app.DTO;

import app.entity.Address;
import app.entity.Store;
import lombok.Data;

public @Data
class CustomerDto {
    private int customerId;
    private int storeId;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDto address;
    private boolean activebool;
    private String createDate;
    private int active;
}
