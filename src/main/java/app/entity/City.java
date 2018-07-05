package app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JsonFormat(pattern="yyyy-MM-dd kk:mm:ss.SS")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime lastUpdate;

    public int getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
