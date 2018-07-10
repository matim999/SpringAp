package app.DTO;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CityDto {
    private int cityId;
    private String city;
    private CountryDto country;

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }
}
