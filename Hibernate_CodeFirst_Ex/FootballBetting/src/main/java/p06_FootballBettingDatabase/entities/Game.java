package p06_FootballBettingDatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game extends BaseEntity{

    @OneToOne
    private Team homeTeam;

    @OneToOne
    private Team awayTeam;

    @Column(name = "home_goals")
    private short homeGoals;

    @Column(name = "away_goals")
    private short awayGoals;

    @Column(name = "date_time_of_game")
    private LocalDateTime dateTimeOfGame;

    @Column(name = "home_team_winBet_rate")
    private Double homeTeamWinBetRate;

    @Column(name = "away_team_winBet_rate")
    private Double awayTeamWinBetRate;

    @Column(name = "draw_game_betRate")
    private Double drawGameBetRate;

    @ManyToOne
    private Round round;

    @ManyToOne
    private Competition competition;



}
