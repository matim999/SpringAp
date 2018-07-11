package app.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_address_id_seq")
    @SequenceGenerator(
            name="address_address_id_seq",
            sequenceName="address_address_id_seq",
            allocationSize = 1
    )
    @EqualsAndHashCode.Exclude private @Getter int addressId;
    private @Getter String address;
    private @Getter String address2;
    private @Getter String district;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "city_id")
    private @Getter City city;
    private @Getter String postalCode;
    private @Getter String phone;
}
