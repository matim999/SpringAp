package app.service;

import app.entity.City;
import app.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import app.repository.CityRepository;
import app.repository.CountryRepository;


@Service
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public HttpStatus updateCityCountryId(int cityId, int countryId)
    {
        City existingCity = cityRepository.findById(cityId).get();
        Country existingCountry = countryRepository.findById(countryId).get();
        if(existingCity != null && existingCity != null) {
            existingCity.setCountry(existingCountry);
            cityRepository.saveAndFlush(existingCity);
            return HttpStatus.OK;
        }
        else if (existingCity != null) {
            Country country = new Country();
            countryRepository.save(country);
            existingCity.setCountry(country);
            cityRepository.saveAndFlush(existingCity);
            return HttpStatus.OK;
        }
        else if (existingCountry != null){
            City city = new City();
            city.setCountry(existingCountry);
            cityRepository.saveAndFlush(city);
            return HttpStatus.OK;
        }
        City city = new City();
        Country country = new Country();
        city.setCountry(country);
        countryRepository.saveAndFlush(country);
        cityRepository.saveAndFlush(city);
        return HttpStatus.OK;
    }

    private HttpStatus updateCityCountry(City city, Country country)
    {
        if(country.getCountry().equals(city.getCountry().getCountry())) {
            return HttpStatus.CONFLICT;
        }
        city.setCountry(country);
        cityRepository.flush();
        return HttpStatus.OK;
    }
    public HttpStatus updateCity(int id, City city)
    {
        City existingCity = cityRepository.findById(id).get();
        Country existingCountry = countryRepository.findByCountry(city.getCountry().getCountry());
        if(existingCity != null && existingCountry != null)
            return updateCityCountry(existingCity, existingCountry);
        else if(existingCity != null) {
            countryRepository.save(city.getCountry());
            existingCountry = countryRepository.findByCountry(city.getCountry().getCountry());
            existingCity.setCountry(existingCountry);
            cityRepository.flush();
            return HttpStatus.OK;
        }
        else if(existingCountry != null){
            city.setCountry(existingCountry);
            cityRepository.save(city);
            return HttpStatus.OK;
        }
        cityRepository.save(city);
        return HttpStatus.OK;
    }

    public HttpStatus deleteCityById(int id)
    {
        cityRepository.deleteById(id);
        return HttpStatus.OK;
    }
    public HttpStatus addNewCity(City city)
    {
        City existingCity = (City) cityRepository.findByCity(city.getCity());
        if(existingCity != null)
            return HttpStatus.CONFLICT;
        cityRepository.save(city);
        return HttpStatus.OK;
    }
}
