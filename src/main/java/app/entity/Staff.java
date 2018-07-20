package app.entity;

import app.DTO.responseDTO.StaffDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.List;

@Entity
@ToString
@Table(name = "Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_staff_id_seq")
    @SequenceGenerator(
            name = "staff_staff_id_seq",
            sequenceName = "staff_staff_id_seq",
            allocationSize = 1
    )
    @Column(name = "staff_id")
    @EqualsAndHashCode.Exclude
    private @Getter
    int staffId;
    private @Getter
    String firstName;
    private @Getter
    String lastName;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private @Getter
    Address address;
    private @Getter
    String email;
    private @Getter
    boolean active;
    private @Getter
    String username;
    private @Getter
    String password;
    @OneToOne(mappedBy = "staff", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private @Getter
    Store store;
    @ManyToMany(
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "staff_rolee",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "rolee_id")
    )
    private @Getter
    List<Rolee> rolees;

    public void update(StaffDto staffDto) {
        this.firstName = staffDto.getFirstName();
        this.lastName = staffDto.getLastName();
        this.email = staffDto.getEmail();
        this.active = staffDto.isActive();
        this.username = staffDto.getUsername();
        this.password = staffDto.getPassword();
    }
}
