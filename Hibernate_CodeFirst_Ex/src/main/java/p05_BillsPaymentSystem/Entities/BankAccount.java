package p05_BillsPaymentSystem.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "bank_accounts")
public class BankAccount extends BillingDetail{

    @Column(nullable = false)
    private String name;

    @Column(name = "swift_code", nullable = false)
    private String swiftCode;

    public BankAccount(String number, User owner, String name, String swiftCode) {
        super(number, owner);
        this.name = name;
        this.swiftCode = swiftCode;
    }

    public BankAccount() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
