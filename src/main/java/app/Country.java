package app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int country_id;
    private String country;

    public int getCountry_id() {
        return country_id;
    }

    public String getCountry() {
        return country;
    }
}
