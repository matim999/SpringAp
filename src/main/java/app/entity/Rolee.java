package app.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
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
    int roleeId;
    private @Getter
    String rolee;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "rolees", cascade = CascadeType.ALL)
    private @Getter
    List<Staff> staff;
}
