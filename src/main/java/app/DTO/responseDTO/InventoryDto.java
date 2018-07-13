package app.DTO.responseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class InventoryDto {
    @EqualsAndHashCode.Exclude
    private int inventoryId;
    private FilmDto film;
    private int storeId;
}
