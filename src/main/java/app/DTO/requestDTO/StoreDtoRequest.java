package app.DTO.requestDTO;

import app.DTO.responseDTO.AddressDto;
import app.DTO.responseDTO.StaffDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class StoreDtoRequest {
    private int staffId;
    private int addressId;
}
