package p06_FootballBettingDatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String email;
    @Column(name = "full_name")
    private String fullName;
    @Column
    private BigDecimal balance;

    public User(String userName, String password, String email, String fullName, BigDecimal balance) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.balance = balance;
    }
}


