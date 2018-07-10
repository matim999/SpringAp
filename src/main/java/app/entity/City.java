package app.entity;

import lombok.Getter;
import lombok.Setter;

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
    private @Getter int cityId;
    private @Getter String city;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "country_id")
    private @Getter @Setter Country country;
}
