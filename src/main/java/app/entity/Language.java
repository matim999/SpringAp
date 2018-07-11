package app.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_language_id_seq")
    @SequenceGenerator(
            name="language_language_id_seq",
            sequenceName="language_language_id_seq",
            allocationSize = 1
    )
    @Column(name = "language_id")
    @EqualsAndHashCode.Exclude private @Getter int languageId;
    private @Getter String name;
}
