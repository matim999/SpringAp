package app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_staff_id_seq")
    @SequenceGenerator(
            name="store_staff_id_seq",
            sequenceName="store_staff_id_seq",
            allocationSize = 1
    )
    private @Getter int staffId;
    private @Getter String firstName;
    private @Getter String lastName;
    private @Getter int addressId;
    private @Getter String email;
    private @Getter boolean active;
    private @Getter String username;
    private @Getter String password;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="store_id")
    private @Getter Store store;
}
