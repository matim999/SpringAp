package app.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Rolee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolee_rolee_id_seq")
    @SequenceGenerator(
            name = "rolee_rolee_id_seq",
            sequenceName = "rolee_rolee_id_seq",
            allocationSize = 1
    )
    @Column(name = "rolee_id")
    private @Getter
    int rolee_id;
    @ManyToMany(mappedBy = "roles")
    private @Getter
    List<Staff> staff;
}
