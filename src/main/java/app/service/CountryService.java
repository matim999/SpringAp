package app.service;

import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import app.repository.CountryRepository;
import app.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static app.DTO.ErrorCode.*;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public HttpStatus addNewCountry(Country country){
        Country existingCountry = countryRepository.findByCountry(country.getCountry()).get();
        if(existingCountry != null)
            throw new ConflictException("Country with given name already exist", COUNTRY_ADD_COUNTRY_WITH_NAME_ALREADY_EXISTS);
        countryRepository.save(country);
        return HttpStatus.OK;
    }

    public HttpStatus deleteCountryById(int id){
        Optional.ofNullable(countryRepository.findById(id)).orElseThrow(() -> new MyNotFoundException("Country with given Id = " + id + " does not exist",
                        COUNTRY_DELETE_COUNTRY_NOT_FOUND));
//        countryRepository.findById(id).orElseThrow(new MyNotFoundException("Country with given Id = " + id + " does not exist",
//                COUNTRY_DELETE_COUNTRY_NOT_FOUND));
        countryRepository.deleteById(id);
        return HttpStatus.OK;
    }

    public HttpStatus updateCountryByID(int id, Country country)
    {
        Country existingCountry = countryRepository.findById(id).orElse(null);
        if(existingCountry != null) {
            if(existingCountry.getCountry().equals(country.getCountry())) {
                throw new ConflictException("County with given Id = " + id + " and name = " + country.getCountry() + " already exists",
                        COUNTRY_UPDATE_COUNTRY_WITH_ID_AND_NAME_ALREADY_EXISTS);
            }
            else if(countryRepository.findByCountry(country.getCountry()).isPresent()){
                throw new ConflictException("County with given name = " + country.getCountry() + " already exists"
                        + countryRepository.findByCountry(country.getCountry()).get().getCountryId() + " Id = "
                        + countryRepository.findByCountry(country.getCountry()).get().getCountry(),
                        COUNTRY_UPDATE_COUNTRY_WITH_NAME_ALREADY_EXISTS);
            }
            existingCountry.setCountry(country.getCountry());
            countryRepository.saveAndFlush(existingCountry);
            return HttpStatus.OK;
        }
        countryRepository.save(country);
        return HttpStatus.OK;
    }
}
