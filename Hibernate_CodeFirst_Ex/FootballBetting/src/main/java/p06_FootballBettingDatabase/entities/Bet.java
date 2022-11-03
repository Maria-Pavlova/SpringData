package p06_FootballBettingDatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bets")
public class Bet extends BaseEntity {

    @Column(name = "bet_money")
    private BigDecimal betMoney;

    @Column(name = "date_and_time_of_bet")
    private LocalDateTime dateAndTimeOfBet;

    @ManyToOne
    private User user;
}
