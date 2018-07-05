package app.entity;

import javax.persistence.*;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_country_id_seq")
    @SequenceGenerator(
            name="country_country_id_seq",
            sequenceName="country_country_id_seq",
            allocationSize = 1
    )
    @Column(name = "country_id")
    private int countryId;
    private String country;

    public int getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
    }
}
