package app;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JsonFormat(pattern="yyyy-MM-dd kk:mm:ss.SS")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime lastUpdate;

    public int getRental_id() {
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
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
