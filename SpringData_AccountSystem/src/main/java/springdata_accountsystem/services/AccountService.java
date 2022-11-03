package springdata_accountsystem.services;

import springdata_accountsystem.exceptions.InsufficientFoundException;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal amount, Long id) throws InsufficientFoundException;
    void depositMoney(BigDecimal amount, Long id);
    void transferBetweenAccounts(Long from, Long to, BigDecimal amount);
}
