package p06_FootballBettingDatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "rounds")
public class Round extends BaseEntity{

    private String name;

}
