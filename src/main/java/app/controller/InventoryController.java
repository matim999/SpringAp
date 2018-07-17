package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.requestDTO.InventoryDtoRequest;
import app.DTO.responseDTO.InventoryDto;
import app.entity.Inventory;
import app.finder.InventoryFinder;
import app.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryController {
    private final InventoryFinder inventoryFinder;
    private final InventoryService inventoryService;
    private final BaseConverter<Inventory, InventoryDto> inventoryConverter;

    @Autowired
    public InventoryController(InventoryFinder inventoryFinder, InventoryService inventoryService, BaseConverter<Inventory, InventoryDto> inventoryConverter) {
        this.inventoryFinder = inventoryFinder;
        this.inventoryService = inventoryService;
        this.inventoryConverter = inventoryConverter;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllInventory() {
        return new ResponseEntity(inventoryConverter.convertAll(inventoryFinder.findAllInventory()), HttpStatus.OK);
    }

    @PostMapping
    private @ResponseBody
    ResponseEntity AddNewInventory(@RequestBody InventoryDtoRequest inventoryDtoRequest) {
        inventoryService.addNewInventory(inventoryDtoRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Inventory> findInventoryById(@PathVariable int id) {
        return new ResponseEntity(inventoryConverter.convertAll(inventoryFinder.findInventoryById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllInventoryByFilmTitle(@RequestParam String title) {
        return new ResponseEntity(inventoryConverter.convertAll(inventoryFinder.findAllInventoryByFilmTitle(title)), HttpStatus.OK);
    }
}
