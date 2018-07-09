package app.finder;

import app.exceptions.MyNotFoundException;
import app.entity.City;
import app.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.repository.CityRepository;
import app.repository.CountryRepository;

import java.util.*;
import java.util.stream.Collectors;
import static app.DTO.ErrorCode.*;

@Component
public class CityFinder {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CityFinder(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }
    public City findById(int id) throws MyNotFoundException {
        City city = cityRepository.findById(id).orElse(null);
        if(city == null)
            throw new MyNotFoundException("City with id does not exist", CITY_NOT_FOUND_USING_ID);
        return city;
    }
    public List findAll()
    {
        return cityRepository.findAll();
    }

    public Country findCityCountry(int id) {
        City existingCity = cityRepository.findById(id)
                .orElseThrow(() -> new MyNotFoundException("City with id does not exist", CITY_NOT_FOUND_USING_ID));
        return existingCity.getCountry();
    }

    public List findCityByCityAndCountry(String city, String country) throws MyNotFoundException {
        List<City> cities = cityRepository.findAll();
        List result = cities.stream()
                .filter(a -> city != null ? a.getCity().equals(city) : true)
                .filter(a -> country != null ? a.getCountry().getCountry().equals(country) : true)
                .collect(Collectors.toList());
        if(result.isEmpty())
            throw new MyNotFoundException("City with given name or country does not exist", CITY_NOT_FOUND_USING_CITY_NAME_OR_COUNTRY_NAME);
        return result;
    }

    public List findCityByCountry(String country) {
        return cityRepository.findByCountry_Country(country);
    }
}
