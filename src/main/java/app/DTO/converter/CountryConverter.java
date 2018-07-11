package app.DTO.converter;

import app.DTO.responseDTO.CountryDto;
import org.springframework.stereotype.Component;
import app.entity.Country;

@Component
public class CountryConverter implements BaseConverter<Country, CountryDto> {
    @Override
    public CountryDto convert(Country from) {
        CountryDto countryDTO = new CountryDto();
        countryDTO.setCountryId(from.getCountryId());
        countryDTO.setCountry(from.getCountry());
        return countryDTO;
    }
}
