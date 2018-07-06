package app.service;

import app.repository.CountryRepository;
import app.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public HttpStatus addNewCountry(Country country){
        Country existingCountry = countryRepository.findByCountry(country.getCountry());
        if(existingCountry != null)
            return HttpStatus.CONFLICT;
        countryRepository.save(country);
        return HttpStatus.OK;
    }

    public HttpStatus deleteCountryById(int id){
        countryRepository.deleteById(id);
        return HttpStatus.OK;
    }

    public HttpStatus updateCountryByID(int id, Country country)
    {
        Country existingCountry = countryRepository.findById(id).get();
        if(existingCountry != null) {
            if(existingCountry.getCountry().equals(country.getCountry())) {
                return HttpStatus.CONFLICT;
            }
            existingCountry = country;
            countryRepository.save(existingCountry);
            countryRepository.flush();
            return HttpStatus.OK;
        }
        countryRepository.save(country);
        return HttpStatus.OK;
    }
}
