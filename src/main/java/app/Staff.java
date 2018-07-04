package app;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Staff")
@PersistenceContext
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_store_id_seq")
    @SequenceGenerator(
            name="store_store_id_seq",
            sequenceName="store_store_id_seq"
    )
    private int staffId;
    private String firstName;
    private String lastName;
    private int addressId;
    private String email;
    private boolean active;
    private String username;
    private String password;
    @JsonFormat(pattern="yyyy-MM-dd kk:mm:ss.SS")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime lastUpdate;
    @OneToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="store_id")
    private Store store;

    public int getStaffId() {
        return staffId;
    }

    public Store getStore() {
        return store;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAddressId() {
        return addressId;
    }
    public String getEmail() {
        return email;
    }
    public boolean isActive() {
        return active;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
