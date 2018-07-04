package app;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name = "Store")
public class Store {
    @Id
    @Column(name = "store_id")
    private int store_id;
    private int manager_staff_id;
    private int address_id;
    private String last_update;

    public int getStore_id() {
        return store_id;
    }
    public int getManager_staff_id() {
        return manager_staff_id;
    }
    public int getAddress_id() {
        return address_id;
    }
    public String getLast_update() {
        return last_update;
    }
}
