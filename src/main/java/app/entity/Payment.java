package app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @Column(name = "payment_id")
    private int paymentId;
    private int customerId;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Rental.class)
    @JoinColumn(name = "rental_id")
    private Rental rental;
    private int amount;
    @JsonFormat(pattern="yyyy-MM-dd kk:mm:ss.SS")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime paymentDate;

    public int getPaymentId() {
        return paymentId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public Staff getStaff() {
        return staff;
    }
    public Rental getRental() {
        return rental;
    }
    public int getAmount() {
        return amount;
    }
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
}
