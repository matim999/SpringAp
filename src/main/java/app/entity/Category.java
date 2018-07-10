package app.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_category_id_seq")
    @SequenceGenerator(
            name="category_category_id_seq",
            sequenceName="category_category_id_seq",
            allocationSize = 1
    )
    @Column(name = "category_id")
    private @Getter int categoryId;
    private @Getter String name;
    @ManyToMany(mappedBy = "categories")
    private @Getter List<Film> films = new ArrayList<>();
}
