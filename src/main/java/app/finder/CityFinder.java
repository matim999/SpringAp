package app.finder;

import app.entity.City;
import app.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import app.repository.CityRepository;
import app.repository.CountryRepository;

import java.util.*;

@Component
public class CityFinder {
    @Autowired
    private final CityRepository cityRepository;
    @Autowired
    private final CountryRepository countryRepository;

    public CityFinder(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }
    public City findById(int id)
    {
        return cityRepository.findById(id).get();
    }
    public List findAll()
    {
        return cityRepository.findAll();
    }
    public ResponseEntity findCountry(int id) {
        return new ResponseEntity(cityRepository.findById(id).get().getCountry(), HttpStatus.FOUND);
    }
    public List findCityByCityAndCountry(String city, String country){
        if(city != "" && country != "")
            return cityRepository.findByCityAndCountry_Country(city, country);
        if(city != "" && country == "")
            return cityRepository.findByCity(city);
        else
            return cityRepository.findByCountry_Country(country);
    }
}
