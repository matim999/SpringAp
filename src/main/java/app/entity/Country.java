package app.entity;

import app.DTO.responseDTO.CityDto;
import app.DTO.responseDTO.CountryDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity @ToString @EqualsAndHashCode
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_country_id_seq")
    @SequenceGenerator(
            name="country_country_id_seq",
            sequenceName="country_country_id_seq",
            allocationSize = 1
    )
    @Column(name = "country_id")
    @EqualsAndHashCode.Exclude private @Getter int countryId;
    private @Getter @Setter String country;

    private void update(CountryDto countryDto){
        this.country = countryDto.getCountry();
    }

    public Country(CountryDto countryDto) {
        this.country = countryDto.getCountry();
    }

    private Country() {
    }
}
