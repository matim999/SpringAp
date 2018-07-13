package app.finder;

import app.entity.Country;
import app.exceptions.MyNotFoundException;
import app.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.ErrorCode.COUNTRY_NOT_FOUND_USING_COUNTRY_NAME;
import static app.ErrorCode.COUNTRY_NOT_FOUND_USING_ID;

@Component
public class CountryFinder {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryFinder(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List findAllCountry() {
        return countryRepository.findAll();
    }

    public Country findCountryById(int id) {
        return countryRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Country with given Id = " + id + ", does not exist", COUNTRY_NOT_FOUND_USING_ID));
    }

    public Country findCountryByCountry(String country) {
        return countryRepository.findByCountry(country).orElseThrow(() -> new MyNotFoundException("Country with given name = " + country + ", does not exist", COUNTRY_NOT_FOUND_USING_COUNTRY_NAME));
    }
}
