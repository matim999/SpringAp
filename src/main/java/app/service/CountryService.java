package app.service;

import app.repository.CountryRepository;
import app.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public ResponseEntity addNewCountry(Country country){
        Country existingCountry = countryRepository.findByCountry(country.getCountry());
        if(existingCountry != null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        countryRepository.save(country);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity deleteCountryById(int id){
        countryRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
