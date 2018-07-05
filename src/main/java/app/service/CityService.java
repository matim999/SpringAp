package app.service;

import app.entity.City;
import app.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ResponseEntity updateCity(City city)
    {
        City existingCity = (City) cityRepository.findByCity(city.getCity()).get(0);
        Country existingCountry = countryRepository.findByCountry(city.getCountry().getCountry());
        if(existingCity != null) {
            if(existingCountry != null) {
                if(existingCountry.getCountry().equals(existingCity.getCountry().getCountry())) {
                    return new ResponseEntity(HttpStatus.CONFLICT);
                }
                existingCity.setCountry(existingCountry);
                return new ResponseEntity(HttpStatus.ACCEPTED);
            }
            else{
                countryRepository.save(city.getCountry());
                existingCountry = countryRepository.findByCountry(city.getCountry().getCountry());
                existingCity.setCountry(existingCountry);
                cityRepository.flush();
                return new ResponseEntity(HttpStatus.ACCEPTED);
            }
        }
        if(existingCountry != null) {
            city.setCountry(existingCountry);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity deleteCityById(int id)
    {
        cityRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    public ResponseEntity addNewCity(City city)
    {
        City existingCity = (City) cityRepository.findByCity(city.getCity());
        if(existingCity != null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        cityRepository.save(city);
        return new ResponseEntity(HttpStatus.OK);
    }
}
