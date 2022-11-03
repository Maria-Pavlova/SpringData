package p06_FootballBettingDatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "teams")
public class Team extends BaseEntity{

    @Column(nullable = false)
    private String name;

    private String logo;

    @Column(length = 3)
    private String initials;

    @OneToOne
    private Color PrimaryKitColor;

    @OneToOne
    private Color SecondaryKitColor;

    @ManyToOne(optional = false)
    private Town town;

    private BigDecimal budget;
}
