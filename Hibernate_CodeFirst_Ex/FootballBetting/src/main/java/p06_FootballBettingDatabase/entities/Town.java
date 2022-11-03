package p06_FootballBettingDatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "towns")
public class Town extends BaseEntity{

    private String name;

    @ManyToOne
    private Country country;




}
