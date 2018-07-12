package app.service;

import app.DTO.responseDTO.CountryDto;
import app.entity.Country;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import app.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static app.ErrorCode.*;

@Service
public class CountryService {
    private final CountryRepository countryRepository;
    private static final Logger logger = Logger.getLogger(CountryService.class.getName());

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void addNewCountry(Country country){
        Country existingCountry = countryRepository.findByCountry(country.getCountry()).orElse(null);
        if(existingCountry != null)
            throw new ConflictException("Country with given name already exist", COUNTRY_ADD_COUNTRY_WITH_NAME_ALREADY_EXISTS);
        countryRepository.save(country);
        logger.info("Added new country: Id = " + country.getCountryId() + " name = " + country.getCountry());
    }

    public void deleteCountryById(int id){
        Country country = countryRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Country with given Id = " + id + " does not exist",
                        COUNTRY_DELETE_COUNTRY_NOT_FOUND));
//        countryRepository.findById(id).orElseThrow(new MyNotFoundException("Country with given Id = " + id + " does not exist",
//                COUNTRY_DELETE_COUNTRY_NOT_FOUND));
        countryRepository.deleteById(id);
        logger.info("Deleted country: Id = " + country.getCountryId() + " name = " + country.getCountry());
    }

    public void updateCountryByID(int id, Country country)
    {
        Country existingCountry = countryRepository.findById(id).orElse(null);
        if(existingCountry != null) {
            if(existingCountry.getCountry().equals(country.getCountry())) {
                throw new ConflictException("County with given Id = " + id + " and name = " + country.getCountry() + " already exists",
                        COUNTRY_UPDATE_COUNTRY_WITH_ID_AND_NAME_ALREADY_EXISTS);
            }
            else if(countryRepository.findByCountry(country.getCountry()).isPresent()){
                throw new ConflictException("County with given name = " + country.getCountry() + " already exists (Id = "
                        + countryRepository.findByCountry(country.getCountry()).get().getCountryId() + ", "
                        + countryRepository.findByCountry(country.getCountry()).get().getCountry() + ")",
                        COUNTRY_UPDATE_COUNTRY_WITH_NAME_ALREADY_EXISTS);
            }
            String recentName = existingCountry.getCountry();
            existingCountry.setCountry(country.getCountry());
            countryRepository.saveAndFlush(existingCountry);
            logger.info("Updated country: Id = " + existingCountry.getCountryId() + " name = " + recentName + " to " + existingCountry.getCountry());
            return;
        }
        countryRepository.save(country);
        logger.info("Added new country: Id = " + country.getCountryId() + " name = " + country.getCountry());
    }

    Country checkForCountry(CountryDto countryDto){
        return countryRepository.findByCountry(countryDto.getCountry()).orElse(new Country(countryDto));
    }
}
