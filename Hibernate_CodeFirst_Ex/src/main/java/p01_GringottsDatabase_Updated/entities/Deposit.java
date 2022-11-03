package p01_GringottsDatabase_Updated.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "deposit_group", length = 20)
    private String depositGroup;

    @Column(name = "deposit_start_date")
    private LocalDateTime depositStartDate;

    @Column(name = "deposit_amount", nullable = false)
    private BigDecimal depositAmount;

    @Column(name = "deposit_interest", nullable = false)
    private BigDecimal depositInterest;

    @Column(name = "deposit_charge", nullable = false)
    private BigDecimal depositCharge;

    @Column(name = "deposit_expiration_date", nullable = false)
    private LocalDateTime depositExpirationDate;

    @Column(name = "is_deposit_expired")
    private boolean isDepositExpired;
}
