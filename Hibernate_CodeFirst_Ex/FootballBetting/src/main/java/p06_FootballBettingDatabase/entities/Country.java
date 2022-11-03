package p06_FootballBettingDatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "countries")
public class Country {
    @Id
    @Column(length = 3)
    private String id;

    private String name;

    @ManyToMany
    private Set<Continent> continents;
}
