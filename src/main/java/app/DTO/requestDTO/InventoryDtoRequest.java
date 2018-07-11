package app.DTO.requestDTO;

import app.DTO.responseDTO.FilmDto;
import app.DTO.responseDTO.StoreDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class InventoryDtoRequest {
    private int filmId;
    private int storeId;
}
