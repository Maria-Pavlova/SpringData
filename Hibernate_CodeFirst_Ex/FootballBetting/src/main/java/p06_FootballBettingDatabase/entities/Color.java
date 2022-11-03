package p06_FootballBettingDatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "colors")
public class Color extends BaseEntity{

    @Column
    private String name;
}
