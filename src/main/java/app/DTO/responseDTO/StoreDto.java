package app.DTO.responseDTO;

import app.DTO.responseDTO.AddressDto;
import app.DTO.responseDTO.StaffDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class StoreDto {
    @EqualsAndHashCode.Exclude private int store_id;
    @EqualsAndHashCode.Exclude private StaffDto staff;
    private AddressDto address;
}
