package app.service;

import app.exceptions.ConflictException;
import app.entity.City;
import app.entity.Country;
import app.exceptions.MyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import app.repository.CityRepository;
import app.repository.CountryRepository;
import static app.DTO.ErrorCode.*;


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
        City existingCity = cityRepository.findById(cityId).orElse(null);
        Country existingCountry = countryRepository.findById(countryId).orElse(null);
        if(existingCity != null && existingCity != null) {
            existingCity.setCountry(existingCountry);
            cityRepository.saveAndFlush(existingCity);
            return HttpStatus.OK;
        }
        else if (existingCity != null) {
            throw new MyNotFoundException("Update city: Not found country with given Id = " + countryId,
                    CITY_UPDATE_COUNTRY_WITH_GIVEN_ID_NOT_FOUND);
        }
        else if (existingCountry != null){
            throw new MyNotFoundException("Update city: Not found city with given Id = " + cityId,
                    CITY_UPDATE_CITY_WITH_GIVEN_ID_NOT_FOUND);
        }
        throw new MyNotFoundException("Update city: Not found city with given Id = " + cityId + " and country with Id = " + countryId,
                CITY_UPDATE_CITY_WITH_GIVEN_ID_AND_COUNTRY_WITH_GIVEN_ID_NOT_FOUND);
    }

    private HttpStatus updateCityCountry(City city, Country country)
    {
        if(country.getCountry().equals(city.getCountry().getCountry())) {
            throw new ConflictException("Can't update data: City with given country already exists", CITY_UPDATE_CITY_WITH_GIVEN_COUNTRY_ALREADY_EXISTS);
        }
        city.setCountry(country);
        cityRepository.flush();
        return HttpStatus.OK;
    }
    public HttpStatus updateCity(int id, City city)
    {
        City existingCity = cityRepository.findById(id).orElse(null);
        Country existingCountry = countryRepository.findByCountry(city.getCountry().getCountry()).get();
        if(existingCity != null && existingCountry != null)
            return updateCityCountry(existingCity, existingCountry);
        else if(existingCity != null) {
            countryRepository.save(city.getCountry());
            existingCountry = countryRepository.findByCountry(city.getCountry().getCountry()).get();
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
        cityRepository.findById(id).orElseThrow(() -> new MyNotFoundException("City with given Id = " + id + " not found", CITY_DELETE_CITY_NOT_FOUND_USING_ID));
        cityRepository.deleteById(id);
        return HttpStatus.OK;
    }
    public HttpStatus addNewCity(City city) throws ConflictException {
        City existingCity = cityRepository.findByCity(city.getCity());
        if(existingCity != null)
            throw new ConflictException("City with that name already exists", CITY_ADD_CITY_WITH_NAME_ALREADY_EXISTS);
        Country existingCountry = countryRepository.findByCountry(city.getCountry().getCountry()).orElse(null);
        if (existingCountry == null)
            throw new MyNotFoundException("Country with given name doesn't exist", CITY_UPDATE_COUNTRY_WITH_GIVEN_NAME_NOT_FOUND);
        cityRepository.saveAndFlush(city);
        return HttpStatus.OK;
    }
}
