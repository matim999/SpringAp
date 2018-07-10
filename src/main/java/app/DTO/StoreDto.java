package app.DTO;

import lombok.Data;

public @Data
class StoreDto {
    private int store_id;
    private StaffDto staff;
    private AddressDto address;
}
