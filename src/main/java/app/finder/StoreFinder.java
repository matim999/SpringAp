package app.finder;

import app.entity.Store;
import app.exceptions.MyNotFoundException;
import app.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.ErrorCode.DIFFERENT;

@Component
public class StoreFinder {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreFinder(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> findAllStore()
    {
        return storeRepository.findAll();
    }

    public Store findStoreById(int id){
        return storeRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Category with given Id not found", DIFFERENT));
    }

    public List<Store> findAllStoreByStaffStaffId(int staffId){
        return storeRepository.findAllByStaffStaffId(staffId).orElseThrow(() -> new MyNotFoundException("Category with given name not found", DIFFERENT));
    }
}
