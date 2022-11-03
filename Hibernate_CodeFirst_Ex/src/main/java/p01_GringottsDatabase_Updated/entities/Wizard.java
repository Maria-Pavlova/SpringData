package p01_GringottsDatabase_Updated.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Wizard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(name = "notes", length = 1000, columnDefinition = "TEXT")
    private String note;

    @Column
    private int age;

    @OneToOne
    @JoinColumn
    private MagicWand magicWands;

    @OneToMany
    private Set<Deposit> deposits;


}
