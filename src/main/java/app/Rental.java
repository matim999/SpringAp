package app;

import javax.persistence.*;

@Entity
public class Rental {
    @Id
    @Column(name = "rental_id")
    private int rentalId;
    private String rentalDate;
    private int inventoryId;
    private int customerId;
    private String returnDate;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "staff_id")
    private Staff staff;
    private String lastUpdate;

    public int getRentald() {
        return rentalId;
    }
    public String getRentalDate() {
        return rentalDate;
    }
    public int getInventoryId() {
        return inventoryId;
    }
    public int customerId() {
        return customerId;
    }
    public String getReturnDate() {
        return returnDate;
    }
    public Staff getStaff() {
        return staff;
    }
    public String getLastUpdate() {
        return lastUpdate;
    }
}
