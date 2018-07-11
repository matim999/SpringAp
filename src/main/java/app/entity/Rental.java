package app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode
public class Rental {
    @Id
    @SequenceGenerator(
            name="rental_rental_id_seq",
            sequenceName="rental_rental_id_seq",
            allocationSize = 1
    )
    @Column(name = "rental_id")
    @EqualsAndHashCode.Exclude private @Getter int rentalId;
    private @Getter String rentalDate;
    @OneToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "inventory_id")
    private @Getter Inventory inventory;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "customer_id")
    private @Getter Customer customer;
    private @Getter String returnDate;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "staff_id")
    private @Getter Staff staff;
}
