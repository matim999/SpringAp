package app.DTO.converter;

import app.DTO.responseDTO.CountryDto;
import app.entity.Country;
import org.springframework.stereotype.Component;

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
