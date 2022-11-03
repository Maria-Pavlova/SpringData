package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "credit_card")
public class CreditCard extends BillingDetail{

    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @Column(name = "expiration_month", nullable = false)
    private Integer expirationMonth;
    @Column(name = "expiration_year", nullable = false)
    private Integer expirationYear;

    public CreditCard(String number, User owner, CardType cardType, Integer expirationMonth, Integer expirationYear) {
        super(number, owner);
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public CreditCard() {
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }
}
