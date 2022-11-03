package springdata_accountsystem.services;

import springdata_accountsystem.exceptions.InsufficientFoundException;
import springdata_accountsystem.models.Account;
import springdata_accountsystem.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public void withdrawMoney(BigDecimal amount, Long id) throws InsufficientFoundException {
        Optional<Account> accountFrom = this.accountRepository
                .findById(id);
        //.orElseThrow() -> new RunTimeException("No account with this id"); // todo

        Account account = getAccount(id);
        if (account.getBalance().compareTo(amount) < 0){
            throw new InsufficientFoundException();
        }
        account.setBalance(account.getBalance().subtract(amount));
        this.accountRepository.save(account);
    }

    @Override
    public void depositMoney(BigDecimal amount, Long id) {

        Account account = getAccount(id);
        if (amount.compareTo(BigDecimal.ZERO) > 0){
            account.setBalance(account.getBalance().add(amount));
            this.accountRepository.save(account);
        }
    }

    @Override
    public void transferBetweenAccounts(Long from, Long to, BigDecimal amount) {

        this.withdrawMoney(amount,from);
        this.depositMoney(amount,to);
    }

    private Account getAccount(Long id) {
        var account =  this.accountRepository
                .findById(id).orElseThrow();
        return account;
    }
}
