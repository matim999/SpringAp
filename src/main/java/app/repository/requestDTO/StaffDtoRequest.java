package app.repository.requestDTO;

import lombok.Data;

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
