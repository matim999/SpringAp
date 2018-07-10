package app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Store")
public class Store {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_store_id_seq")
    @SequenceGenerator(
            name="store_store_id_seq",
            sequenceName="store_store_id_seq",
            allocationSize = 1
    )
    private @Getter int store_id;
    private @Getter int managerStaffId;
    private @Getter int addressId;
}
