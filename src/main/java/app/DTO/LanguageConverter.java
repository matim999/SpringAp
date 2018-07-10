package app.DTO;

import app.entity.Actor;
import app.entity.Category;
import app.entity.Film;
import app.entity.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageConverter implements BaseConverter<Language, LanguageDto> {

    @Override
    public LanguageDto convert(Language from) {
        LanguageDto languageDto = new LanguageDto();
        languageDto.setLanguageId(from.getLanguageId());
        languageDto.setName(from.getName());
        return languageDto;
    }
}
