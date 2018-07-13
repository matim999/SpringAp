package app.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "Store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_store_id_seq")
    @SequenceGenerator(
            name = "store_store_id_seq",
            sequenceName = "store_store_id_seq",
            allocationSize = 1
    )
    @Column(name = "store_id")
    @EqualsAndHashCode.Exclude
    private @Getter
    int store_id;
    @OneToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "staff_id")
    @PrimaryKeyJoinColumn
    private @Getter
    Staff staff;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private @Getter
    Address address;
}
