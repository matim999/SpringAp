package app.finder;

import app.entity.Film;
import app.entity.Inventory;
import app.exceptions.MyNotFoundException;
import app.repository.FilmRepository;
import app.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.DTO.ErrorCode.DIFFERENT;

@Component
public class InventoryFinder {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryFinder(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> findAllInventory()
    {
        return inventoryRepository.findAll();
    }

    public Inventory findInventoryById(int id){
        return inventoryRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Category with given Id not found", DIFFERENT));
    }

    public List<Inventory> findAllInventoryByFilmTitle(String title){
        return inventoryRepository.findAllByFilmTitle(title).orElseThrow(() -> new MyNotFoundException("Category with given name not found", DIFFERENT));
    }
}
