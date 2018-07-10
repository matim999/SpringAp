package app.DTO;

import app.entity.Film;
import app.entity.Store;
import lombok.Data;
import lombok.EqualsAndHashCode;

public @Data
class InventoryDto {
    @EqualsAndHashCode.Exclude private int inventoryId;
    private FilmDto film;
    private StoreDto store;
}
