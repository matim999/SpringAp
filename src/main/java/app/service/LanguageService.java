package app.service;

import app.DTO.converter.BaseConverter;
import app.DTO.converter.ToBaseConverter;
import app.DTO.requestDTO.LanguageDtoRequest;
import app.DTO.responseDTO.LanguageDto;
import app.ErrorCode;
import app.entity.Language;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import app.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final ToBaseConverter<LanguageDtoRequest, LanguageDto> languageRequestConverter;
    private final BaseConverter<Language, LanguageDto> languageConverter;

    public LanguageService(LanguageRepository languageRepository, ToBaseConverter<LanguageDtoRequest, LanguageDto> languageRequestConverter, BaseConverter<Language, LanguageDto> languageConverter) {
        this.languageRepository = languageRepository;
        this.languageRequestConverter = languageRequestConverter;
        this.languageConverter = languageConverter;
    }

    public void addNewLanguage(LanguageDtoRequest languageDtoRequest) {
        Collection<Language> collection = languageRepository.findAllByName(languageDtoRequest.getName()).orElse(new ArrayList<>());
        if (collection.isEmpty()) {
            languageRepository.save(new Language(languageRequestConverter.convertAllToBase(languageDtoRequest)));
            return;
        }
        LanguageDto languageDto = languageRequestConverter.convertAllToBase(languageDtoRequest);
        Collection<LanguageDto> language = languageConverter.convertAll(collection);
        language.forEach(a -> {
            if (a.equals(languageDto))
                throw new ConflictException("Actor with given name nad surname already exists", ErrorCode.DIFFERENT);
        });
        languageRepository.save(new Language(languageRequestConverter.convertAllToBase(languageDtoRequest)));
    }

    public void deleteLanguageById(int id) {
        languageRepository.findById(id).orElseThrow(() -> new MyNotFoundException("No Actor With Given Id", ErrorCode.DIFFERENT));
        languageRepository.deleteById(id);
    }
}
