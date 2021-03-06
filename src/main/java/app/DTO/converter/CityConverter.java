package app.DTO.converter;

import app.DTO.responseDTO.CityDto;
import app.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return cityDto;
    }
}
