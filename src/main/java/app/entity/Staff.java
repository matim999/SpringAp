package app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode
@Table(name = "Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_staff_id_seq")
    @SequenceGenerator(
            name="staff_staff_id_seq",
            sequenceName="staff_staff_id_seq",
            allocationSize = 1
    )
    @Column(name = "staff_id")
    @EqualsAndHashCode.Exclude private @Getter int staffId;
    private @Getter String firstName;
    private @Getter String lastName;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private @Getter Address address;
    private @Getter String email;
    private @Getter boolean active;
    private @Getter String username;
    private @Getter String password;
    @OneToOne(mappedBy = "staff", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private @Getter Store store;
}
