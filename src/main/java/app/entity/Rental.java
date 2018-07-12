package app.entity;

import app.DTO.responseDTO.RentalDto;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_rental_id_seq")
    @SequenceGenerator(
            name="rental_rental_id_seq",
            sequenceName="rental_rental_id_seq",
            allocationSize = 1
    )
    @Column(name = "rental_id")
    @EqualsAndHashCode.Exclude private @Getter int rentalId;
    @JsonFormat(pattern="yyyy-MM-dd kk:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private @Getter LocalDateTime rentalDate;
    @OneToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "inventory_id")
    private @Getter Inventory inventory;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "customer_id")
    private @Getter Customer customer;
    @JsonFormat(pattern="yyyy-MM-dd kk:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private @Getter LocalDateTime returnDate;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "staff_id")
    private @Getter Staff staff;

    public Rental(RentalDto rentalDto, Inventory inventory, Customer customer, Staff staff) {
        this.rentalDate = rentalDto.getRentalDate();
        this.inventory = inventory;
        this.customer = customer;
        this.returnDate = rentalDto.getReturnDate();
        this.staff = staff;
    }

    public Rental(RentalDto rentalDto) {
        this.rentalDate = rentalDto.getRentalDate();
        this.returnDate = rentalDto.getReturnDate();
    }

    public Rental() {
        super();
    }
}
