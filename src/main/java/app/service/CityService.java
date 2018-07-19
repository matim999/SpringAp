package app.service;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.CityDto;
import app.DTO.responseDTO.CountryDto;
import app.ErrorCode;
import app.entity.City;
import app.entity.Country;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import app.repository.CityRepository;
import app.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import static app.ErrorCode.*;


@Service
public class CityService {
    private static final Logger logger = Logger.getLogger(CountryService.class.getName());
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CountryService countryService;
    private final BaseConverter<City, CityDto> cityConverter;

    @Autowired
    public CityService(CityRepository cityRepository, CountryRepository countryRepository, CountryService countryService, BaseConverter<City, CityDto> cityConverter) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.countryService = countryService;
        this.cityConverter = cityConverter;
    }

    public void updateCityCountryId(int cityId, int countryId) {
        City existingCity = cityRepository.findById(cityId).orElse(null);
        Country existingCountry = countryRepository.findById(countryId).orElse(null);
        if (existingCity != null && existingCity != null) {
            if (existingCountry.getCountryId() == existingCity.getCountry().getCountryId())
                throw new ConflictException("City update country: City with given cityId = " + cityId + " already has a Country with given countryId = " + countryId,
                        CITY_UPDATE_CITY_ID_ALREADY_HAS_THE_SAME_COUNTRY_ID);
            Country recentCountry = existingCity.getCountry();
            existingCity.setCountry(existingCountry);
            cityRepository.saveAndFlush(existingCity);
            logger.info("Updated country for City: cityId = " + existingCity.getCityId() + " name = " + existingCity.getCity() + " from countryId = "
                    + recentCountry.getCountryId() + "(" + recentCountry.getCountry() + ") to countryId = " + recentCountry.getCountryId() + "(" + recentCountry.getCountry() + ")");
            return;
        } else if (existingCity != null) {
            throw new MyNotFoundException("Update city: Not found country with given Id = " + countryId,
                    CITY_UPDATE_COUNTRY_WITH_GIVEN_ID_NOT_FOUND);
        } else if (existingCountry != null) {
            throw new MyNotFoundException("Update city: Not found city with given Id = " + cityId,
                    CITY_UPDATE_CITY_WITH_GIVEN_ID_NOT_FOUND);
        }
        throw new MyNotFoundException("Update city: Not found city with given Id = " + cityId + " and country with Id = " + countryId,
                CITY_UPDATE_CITY_WITH_GIVEN_ID_AND_COUNTRY_WITH_GIVEN_ID_NOT_FOUND);
    }

    private void updateCityCountry(City city, Country country) {
        if (country.getCountry().equals(city.getCountry().getCountry())) {
            throw new ConflictException("Can't update data: City with given country already exists", CITY_UPDATE_CITY_WITH_GIVEN_COUNTRY_ALREADY_EXISTS);
        }
        Country recentCountry = city.getCountry();
        city.setCountry(country);
        cityRepository.flush();
        logger.info("Updated country for City: cityId = " + city.getCityId() + " name = " + city.getCity() + " from countryId = "
                + recentCountry.getCountryId() + "(" + recentCountry.getCountry() + ") to countryId = " + recentCountry.getCountryId() + "(" + recentCountry.getCountry() + ")");
    }

    public void updateCity(int id, City city) {
        City existingCity = cityRepository.findById(id).orElse(null);
        if (existingCity == null && cityRepository.findByCity(city.getCity()) != null)
            throw new ConflictException("City update: City with given name already exists : cityId = " + cityRepository.findByCity(city.getCity()).get().get(0) + "("
                    + cityRepository.findByCity(city.getCity()).get().get(0) + ")", CITY_UPDATE_CITY_WITH_GIVEN_NAME_ALREADY_EXISTS);
        Country existingCountry = countryRepository.findByCountry(city.getCountry().getCountry()).orElse(null);
        if (existingCity != null && existingCountry != null) {
            updateCityCountry(existingCity, existingCountry);
            return;
        } else if (existingCity != null) {
            countryRepository.save(city.getCountry());
            existingCountry = countryRepository.findByCountry(city.getCountry().getCountry()).orElse(null);
            existingCity.setCountry(existingCountry);
            cityRepository.flush();
            logger.info("Updated country for City: cityId = " + city.getCityId() + " name = " + city.getCity() + ", added new country countryId = "
                    + existingCountry.getCountryId() + "(" + existingCountry.getCountry() + ")");
            return;
        } else if (existingCountry != null) {
            city.setCountry(existingCountry);
            cityRepository.save(city);
            logger.info("Added new city: cityId = " + city.getCityId() + " name = " + city.getCity() + ", set countryId = "
                    + existingCountry.getCountryId() + "(" + existingCountry.getCountry() + ")");
            return;
        }
        cityRepository.save(city);
        logger.info("Added new city: cityId = " + city.getCityId() + " name = " + city.getCity() + ", added new countryId = "
                + existingCity.getCountry().getCountryId() + "(" + existingCity.getCountry().getCountry() + ")");
    }

    public void deleteCityById(int id) {
        City deletedCity = cityRepository.findById(id).orElseThrow(() -> new MyNotFoundException("City with given Id = " + id + " not found", CITY_DELETE_CITY_NOT_FOUND_USING_ID));
        cityRepository.deleteById(id);
        logger.info("Deleted country: Id = " + id + " name = " + deletedCity.getCity());
    }

    public void addNewCity(City city) {
        Collection existingCity = cityRepository.findByCity(city.getCity()).orElse(null);
        if (existingCity != null)
            throw new ConflictException("City with that name already exists", CITY_ADD_CITY_WITH_NAME_ALREADY_EXISTS);
        Country existingCountry = countryRepository.findByCountry(city.getCountry().getCountry()).orElse(null);
        if (existingCountry == null)
            throw new MyNotFoundException("Country with given name doesn't exist", CITY_UPDATE_COUNTRY_WITH_GIVEN_NAME_NOT_FOUND);
        cityRepository.saveAndFlush(city);
        logger.info("Added new city: Id = " + city.getCityId() + " name = " + city.getCity());
    }

    public void addCity(City city){
        CityDto cityDto = cityConverter.convertAll(city);
        Collection<CityDto> existingCities = cityConverter.convertAll(cityRepository.findByCity(cityDto.getCity()).orElse(new ArrayList<>()));
        if (!existingCities.isEmpty()) {
            if ( existingCities
                    .stream()
                    .filter(a -> a.equals(cityDto))
                    .findFirst()
                    .isPresent() )
                throw new ConflictException("This address already exists", ErrorCode.DIFFERENT);
        }
        checkForCity(cityDto);
    }

    public City checkForCity(CityDto cityDto) {
        CountryDto countryDto = cityDto.getCountry();
        Collection<CityDto> existingCities = cityConverter.convertAll(cityRepository.findByCity(cityDto.getCity()).orElse(new ArrayList<>()));
        if (existingCities.isEmpty()) {
            logger.info("1");
            return saveCity(cityDto, countryDto);
        }
        return existingCities
                .stream()
                .filter(a -> a.equals(cityDto))
                .findFirst()
                .map(a -> cityRepository.findById(a.getCityId()).get())
                .orElseGet(() -> saveCity(cityDto, countryDto));
    }

    private City saveCity(CityDto cityDto, CountryDto countryDto) {
        Country country = countryService.checkForCountry(countryDto);
        return cityRepository.saveAndFlush(new City(cityDto, country));
    }
}
