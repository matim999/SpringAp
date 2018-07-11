package app.DTO.converter;

import app.DTO.converter.BaseConverter;
import app.DTO.requestDTO.LanguageDtoRequest;
import app.DTO.responseDTO.LanguageDto;
import app.entity.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageConverter implements BaseConverter<Language, LanguageDto>, ToBaseConverter<LanguageDtoRequest, LanguageDto> {

    @Override
    public LanguageDto convert(Language from) {
        LanguageDto languageDto = new LanguageDto();
        languageDto.setLanguageId(from.getLanguageId());
        languageDto.setName(from.getName());
        return languageDto;
    }

    @Override
    public LanguageDto convertToBase(LanguageDtoRequest from) {
        LanguageDto languageDto = new LanguageDto();
        languageDto.setName(from.getName());
        return languageDto;
    }
}
