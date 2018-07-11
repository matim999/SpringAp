package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_inventory_id_seq")
    @SequenceGenerator(
            name="inventory_inventory_id_seq",
            sequenceName="inventory_inventory_id_seq",
            allocationSize = 1
    )
    @Column(name = "inventory_id")
    @EqualsAndHashCode.Exclude private @Getter int inventoryId;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "film_id")
    private @Getter Film film;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private @Getter Store store;
}
