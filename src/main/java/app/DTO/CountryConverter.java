package app.DTO;

import org.springframework.stereotype.Component;
import app.entity.Country;

import java.util.ArrayList;
import java.util.List;

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
