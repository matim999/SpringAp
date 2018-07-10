package app.finder;

import app.entity.Inventory;
import app.entity.Language;
import app.exceptions.MyNotFoundException;
import app.repository.InventoryRepository;
import app.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.DTO.ErrorCode.DIFFERENT;

@Component
public class LanguageFinder {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageFinder(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> findAllLanguage()
    {
        return languageRepository.findAll();
    }

    public Language findLanguageById(int id){
        return languageRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Category with given Id not found", DIFFERENT));
    }

    public List<Language> findAllLanguageByName(String name){
        return languageRepository.findAllByName(name).orElseThrow(() -> new MyNotFoundException("Category with given name not found", DIFFERENT));
    }
}
