package app.entity;

import javax.persistence.*;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_city_id_seq")
    @SequenceGenerator(
            name="city_city_id_seq",
            sequenceName="city_city_id_seq",
            allocationSize = 1
    )
    @Column(name = "city_id")
    private int cityId;
    private String city;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "country_id")
    private Country country;

    public int getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
