package app.DTO.converter;

import app.repository.requestDTO.InventoryDtoRequest;
import app.DTO.responseDTO.FilmDto;
import app.DTO.responseDTO.InventoryDto;
import app.ErrorCode;
import app.entity.Film;
import app.entity.Inventory;
import app.exceptions.MyNotFoundException;
import app.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryConverter implements BaseConverter<Inventory, InventoryDto>, ToBaseConverter<InventoryDtoRequest, InventoryDto> {
    private final BaseConverter<Film, FilmDto> filmConverter;
    private final FilmRepository filmRepository;

    @Autowired
    public InventoryConverter(BaseConverter<Film, FilmDto> filmConverter, FilmRepository filmRepository) {
        this.filmConverter = filmConverter;
        this.filmRepository = filmRepository;
    }

    @Override
    public InventoryDto convert(Inventory from) {
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setInventoryId(from.getInventoryId());
        inventoryDto.setFilm(filmConverter.convertAll(from.getFilm()));
        inventoryDto.setStoreId(from.getStoreId());
        return inventoryDto;
    }

    @Override
    public InventoryDto convertToBase(InventoryDtoRequest from) {
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setFilm(filmConverter.convertAll(filmRepository.findById(from.getFilmId())
                .orElseThrow(() -> new MyNotFoundException("Address with given ID not found", ErrorCode.DIFFERENT))));
        inventoryDto.setStoreId(from.getStoreId());
        return inventoryDto;
    }
}
