package app;

import javax.persistence.*;

@Entity
public class Country {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_country_id_seq")
    @SequenceGenerator(
            name="country_country_id_seq",
            sequenceName="country_country_id_seq"
    )
    private int countryId;
    private String country;

    public int getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
    }
}
