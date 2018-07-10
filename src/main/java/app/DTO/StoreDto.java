package app.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class StoreDto {
    @EqualsAndHashCode.Exclude private int store_id;
    private StaffDto staff;
    private AddressDto address;
}
