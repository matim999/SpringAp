package app.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@EqualsAndHashCode @ToString
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_city_id_seq")
    @SequenceGenerator(
            name="city_city_id_seq",
            sequenceName="city_city_id_seq",
            allocationSize = 1
    )
    @Column(name = "city_id")
    @EqualsAndHashCode.Exclude private @Getter int cityId;
    private @Getter String city;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "country_id")
    private @Getter @Setter Country country;
}
