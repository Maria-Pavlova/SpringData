package p06_FootballBettingDatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "continents")
public class Continent extends BaseEntity{

    private String name;

    @ManyToMany(mappedBy = "continents", targetEntity = Country.class)
    private Set<Country> countries;
}
