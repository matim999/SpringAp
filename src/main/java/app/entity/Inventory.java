package app.entity;

import app.repository.requestDTO.InventoryDtoRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_inventory_id_seq")
    @SequenceGenerator(
            name = "inventory_inventory_id_seq",
            sequenceName = "inventory_inventory_id_seq",
            allocationSize = 1
    )
    @Column(name = "inventory_id")
    @EqualsAndHashCode.Exclude
    private @Getter
    int inventoryId;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "film_id")
    private @Getter
    Film film;
    private @Getter
    int storeId;

    public Inventory(InventoryDtoRequest inventoryDtoRequest, Film film) {
        this.film = film;
        this.storeId = inventoryDtoRequest.getStoreId();
    }

    private Inventory() {
    }

    ;
}
