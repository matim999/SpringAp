package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.StoreDto;
import app.entity.Store;
import app.finder.StoreFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreFinder storeFinder;
    private final BaseConverter<Store, StoreDto> storeConverter;

    @Autowired
    public StoreController(StoreFinder storeFinder, BaseConverter<Store, StoreDto> storeConverter) {
        this.storeFinder = storeFinder;
        this.storeConverter = storeConverter;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllStore()
    {
        return new ResponseEntity(storeConverter.convertAll(storeFinder.findAllStore()), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Store> findStoreById(@PathVariable int id) {
        return new ResponseEntity(storeConverter.convertAll(storeFinder.findStoreById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllStoreByStaffId(@RequestParam int staffId) {
        return new ResponseEntity(storeConverter.convertAll(storeFinder.findAllStoreByStaffStaffId(staffId)), HttpStatus.OK);
    }
}