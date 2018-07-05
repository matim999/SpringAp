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

    public HttpStatus updateCity(City city)
    {
        City existingCity = (City) cityRepository.findByCity(city.getCity()).get(0);
        Country existingCountry = countryRepository.findByCountry(city.getCountry().getCountry());
        if(existingCity != null) {
            if(existingCountry != null) {
                if(existingCountry.getCountry().equals(existingCity.getCountry().getCountry())) {
                    return HttpStatus.CONFLICT;
                }
                existingCity.setCountry(existingCountry);
                return HttpStatus.OK;
            }
            else{
                countryRepository.save(city.getCountry());
                existingCountry = countryRepository.findByCountry(city.getCountry().getCountry());
                existingCity.setCountry(existingCountry);
                cityRepository.flush();
                return HttpStatus.OK;
            }
        }
        if(existingCountry != null) {
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
