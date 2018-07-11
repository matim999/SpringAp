package app.DTO.converter;

import app.DTO.responseDTO.FilmDto;
import app.DTO.responseDTO.InventoryDto;
import app.DTO.responseDTO.StoreDto;
import app.entity.Film;
import app.entity.Inventory;
import app.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryConverter implements BaseConverter<Inventory, InventoryDto> {
    private final BaseConverter<Store, StoreDto> storeConverter;
    private final BaseConverter<Film, FilmDto> filmConverter;

    @Autowired
    public InventoryConverter(BaseConverter<Store, StoreDto> storeConverter, BaseConverter<Film, FilmDto> filmConverter) {
        this.storeConverter = storeConverter;
        this.filmConverter = filmConverter;
    }

    @Override
    public InventoryDto convert(Inventory from) {
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setInventoryId(from.getInventoryId());
        inventoryDto.setFilm(filmConverter.convertAll(from.getFilm()));
        inventoryDto.setStore(storeConverter.convertAll(from.getStore()));
        return inventoryDto;
    }
}
