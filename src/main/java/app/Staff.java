package app;

import controller.StoreController;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Staff")
public class Staff {
    @Id
    @Column(name = "staff_id")
    private int staff_id;
    private String first_name;
    private String last_name;
    private int address_id;
    private String email;
    private boolean active;
    private String username;
    private String password;
    private String last_update;
    @OneToOne
    @JoinColumn(name="store_id")
    private Store store;

    public int getStaff_id() {
        return staff_id;
    }

    public Store getStore() {
        return store;
    }
    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public int getAddress_id() {
        return address_id;
    }
    public String getEmail() {
        return email;
    }
    public boolean isActive() {
        return active;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getLast_update() {
        return last_update;
    }
}
