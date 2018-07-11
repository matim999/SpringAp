package app.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
//gg
@Entity
@EqualsAndHashCode
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_customer_id_seq")
    @SequenceGenerator(
            name="customer_customer_id_seq",
            sequenceName="customer_customer_id_seq",
            allocationSize = 1
    )
    @Column(name = "customer_id")
    @EqualsAndHashCode.Exclude private @Getter int customerId;
    private @Getter int storeId;
    private @Getter String firstName;
    private @Getter String lastName;
    private @Getter String email;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private @Getter Address address;
    private @Getter boolean activebool;
    private @Getter String createDate;
    private @Getter int active;
}
