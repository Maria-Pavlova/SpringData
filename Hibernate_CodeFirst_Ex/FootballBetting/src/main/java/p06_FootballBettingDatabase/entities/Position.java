package p06_FootballBettingDatabase.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "positions")
public class Position {
    @Id
    @Column(length = 2)
    private String id;

    private String positionDescription;
}
