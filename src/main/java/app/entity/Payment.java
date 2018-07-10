package app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @Column(name = "payment_id")
    private @Getter int paymentId;
    private @Getter int customerId;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "staff_id")
    private @Getter Staff staff;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Rental.class)
    @JoinColumn(name = "rental_id")
    private @Getter Rental rental;
    private @Getter int amount;
}
