package app.DTO.responseDTO;

import app.DTO.responseDTO.AddressDto;
import app.DTO.responseDTO.StaffDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class StoreDto {
    @EqualsAndHashCode.Exclude private int store_id;
    @EqualsAndHashCode.Exclude @JsonIgnore
    private StaffDto staff;
    @JsonIgnore private AddressDto address;
}
