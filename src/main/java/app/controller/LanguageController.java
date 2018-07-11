package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.LanguageDto;
import app.entity.Language;
import app.finder.LanguageFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/language")
public class LanguageController {
    private final LanguageFinder languageFinder;
    private final BaseConverter<Language, LanguageDto> languageConverter;

    @Autowired
    public LanguageController(LanguageFinder inventoryFinder, BaseConverter<Language, LanguageDto> languageConverter) {
        this.languageFinder = inventoryFinder;
        this.languageConverter = languageConverter;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllLanguage()
    {
        return new ResponseEntity(languageConverter.convertAll(languageFinder.findAllLanguage()), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Language> findLanguageById(@PathVariable int id) {
        return new ResponseEntity(languageConverter.convertAll(languageFinder.findLanguageById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllLanguageByName(@RequestParam String name) {
        return new ResponseEntity(languageConverter.convertAll(languageFinder.findAllLanguageByName(name)), HttpStatus.OK);
    }
}
