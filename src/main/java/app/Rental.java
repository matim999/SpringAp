package app;

import javax.persistence.*;

@Entity
public class Rental {
    @Id
    @Column(name = "rental_id")
    private int rental_id;
    private String rental_date;
    private int inventory_id;
    private int customer_id;
    private String return_date;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "staff_id")
    private Staff staff;
    private String last_update;

    public int getRental_id() {
        return rental_id;
    }
    public String getRental_date() {
        return rental_date;
    }
    public int getInventory_id() {
        return inventory_id;
    }
    public int getCustomer_id() {
        return customer_id;
    }
    public String getReturn_date() {
        return return_date;
    }
    public Staff getStaff() {
        return staff;
    }
    public String getLast_update() {
        return last_update;
    }
}
