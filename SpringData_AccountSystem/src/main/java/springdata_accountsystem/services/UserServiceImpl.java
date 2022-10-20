package springdata_accountsystem.services;

import springdata_accountsystem.exceptions.UserNotFoundException;
import springdata_accountsystem.models.Account;
import springdata_accountsystem.models.User;
import springdata_accountsystem.repositories.AccountRepository;
import springdata_accountsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerUser(User user) {
//       Optional<User> found = Optional.ofNullable(this.userRepository
//               .findByUsername(user.getUsername()));
//       if (found.isEmpty()){
//            this.userRepository.save(user);
//        }


        if (!userRepository.existsByUsername(user.getUsername())){
            this.userRepository.save(user);
        }

    }

    @Override
    public void addAccount(BigDecimal amount, long id) throws UserNotFoundException {
        User user = this.userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        Account newAccount = new Account();
        newAccount.setBalance(amount);
        newAccount.setUser(user);
        this.accountRepository.save(newAccount);
    }
}
