package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_category_id_seq")
    @SequenceGenerator(
            name="category_category_id_seq",
            sequenceName="category_category_id_seq",
            allocationSize = 1
    )
    @Column(name = "category_id")
    @EqualsAndHashCode.Exclude private @Getter int categoryId;
    private @Getter String name;
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude private List<Film> films = new ArrayList<>();

    public List<Film> getFilms() {
        return films;
    }
}
