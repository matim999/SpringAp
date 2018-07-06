package app.DTO;

import app.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityConverter implements BaseConverter<City, CityDto> {
    private final CountryConverter countryConverter;

    @Autowired
    public CityConverter(CountryConverter countryConverter) {
        this.countryConverter = countryConverter;
    }

    @Override
    public CityDto convert(City from) {
        CityDto cityDto = new CityDto();
        cityDto.setCityId(from.getCityId());
        cityDto.setCity(from.getCity());
        cityDto.setCountry(countryConverter.convert(from.getCountry()));
        cityDto.setLastUpdate(from.getLastUpdate());
        return cityDto;
    }

    public List convert(List<City> cities) {
        List cityDtos = new ArrayList();
        for (City city : cities) {
            cityDtos.add(convert(city));
        }
        return cityDtos;
    }
}
