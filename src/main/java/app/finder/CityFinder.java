package app.finder;

import app.entity.City;
import app.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import app.repository.CityRepository;
import app.repository.CountryRepository;

import java.util.*;

@Component
public class CityFinder {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
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
    public Country findCityCountry(int id) {
        return cityRepository.findById(id).get().getCountry();
    }
    public List findCityByCityAndCountry(String city, String country){
        if(city != "" && country != "")
            return cityRepository.findByCityAndCountry_Country(city, country);
        if(city != "" && country == "")
            return cityRepository.findByCity(city);
        else
            return cityRepository.findByCountry_Country(country);
    }

    public List findCityByCountry(String country) {
        return cityRepository.findByCountry_Country(country);
    }
}
