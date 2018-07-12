package app.service;

import app.DTO.converter.BaseConverter;
import app.DTO.converter.ToBaseConverter;
import app.DTO.requestDTO.InventoryDtoRequest;
import app.DTO.responseDTO.ActorDto;
import app.DTO.responseDTO.InventoryDto;
import app.ErrorCode;
import app.entity.Actor;
import app.entity.Inventory;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import app.repository.FilmRepository;
import app.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final FilmRepository filmRepository;
    private final BaseConverter<Inventory, InventoryDto> inventoryConverter;
    private final ToBaseConverter<InventoryDtoRequest, InventoryDto> inventoryRequestConverter;

    public InventoryService(InventoryRepository inventoryRepository, FilmRepository filmRepository, BaseConverter<Inventory, InventoryDto> inventoryConverter, ToBaseConverter<InventoryDtoRequest, InventoryDto> inventoryRequestConverter) {
        this.inventoryRepository = inventoryRepository;
        this.filmRepository = filmRepository;
        this.inventoryConverter = inventoryConverter;
        this.inventoryRequestConverter = inventoryRequestConverter;
    }

    public void addNewInventory(InventoryDtoRequest inventoryDtoRequest) {
        Collection<Inventory> collection = inventoryRepository.findAllByStoreIdAndFilmFilmId(inventoryDtoRequest.getStoreId(), inventoryDtoRequest.getFilmId()).orElse(new ArrayList<>());
        if(collection.isEmpty()){
            saveInventory(inventoryDtoRequest);
            return;
        }
        InventoryDto inventoryDto = inventoryRequestConverter.convertAllToBase(inventoryDtoRequest);
        Collection<InventoryDto> inventory = inventoryConverter.convertAll(collection);
        inventory.forEach(a -> {
            if (a.equals(inventoryDto))
                throw new ConflictException("That inventory already exists", ErrorCode.DIFFERENT);
        });
        saveInventory(inventoryDtoRequest);
    }

    private void saveInventory(InventoryDtoRequest inventoryDtoRequest){
        inventoryRepository.save(new Inventory(inventoryDtoRequest,
                filmRepository.findById(inventoryDtoRequest.getFilmId())
                        .orElseThrow(() -> new MyNotFoundException("Film with given Id not found", ErrorCode.DIFFERENT))));
    }
}
