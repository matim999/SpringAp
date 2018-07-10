package app.DTO;

import app.entity.Film;
import app.entity.Store;
import lombok.Data;

public @Data
class InventoryDto {
    private int inventoryId;
    private FilmDto film;
    private StoreDto store;
}
