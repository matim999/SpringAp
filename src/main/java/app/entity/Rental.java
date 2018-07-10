package app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Rental {
    @Id
    @Column(name = "rental_id")
    private @Getter int rentalId;
    private @Getter String rentalDate;
    private @Getter int inventoryId;
    private @Getter int customerId;
    private @Getter String returnDate;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "staff_id")
    private @Getter Staff staff;
}
