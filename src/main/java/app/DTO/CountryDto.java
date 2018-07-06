package app.DTO;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
public class CountryDto {
    private int countryId;
    private String country;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
