package app.finder;

import app.entity.Country;
import app.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryFinder {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryFinder(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    public List findAllCountry()
    {
        return countryRepository.findAll();
    }
    public Country findCountryById(int id)
    {
        return countryRepository.findById(id).get();
    }
    public Country findCountryByCountry(String country)
    {
        return countryRepository.findByCountry(country);
    }
}
