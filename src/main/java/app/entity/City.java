package app.entity;

import app.DTO.responseDTO.CityDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_city_id_seq")
    @SequenceGenerator(
            name = "city_city_id_seq",
            sequenceName = "city_city_id_seq",
            allocationSize = 1
    )
    @Column(name = "city_id")
    @EqualsAndHashCode.Exclude
    private int cityId;
    private String city;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "country_id")
    private Country country;

    public City(CityDto cityDto, Country country) {
        this.city = cityDto.getCity();
        this.country = country;
    }

    public City() {
        super();
    }

    public void update(CityDto cityDto) {
        this.city = cityDto.getCity();
    }
}
