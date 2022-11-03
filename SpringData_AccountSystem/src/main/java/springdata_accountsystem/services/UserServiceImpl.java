package springdata_accountsystem.services;

import springdata_accountsystem.exceptions.UserNotFoundException;
import springdata_accountsystem.models.Account;
import springdata_accountsystem.models.User;
import springdata_accountsystem.repositories.AccountRepository;
import springdata_accountsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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
    public void registerUser(String userName, int age) {

        if (userName.isBlank() || age < 18){
            throw new RuntimeException("Validation failed");
        }

        Optional<User> byUsername = this.userRepository.findByUsername(userName);
        if (byUsername.isPresent()) {
            throw new RuntimeException("Username already in use");
        }

        Account account = new Account();
        User user = new User(userName, age, account);

        this.userRepository.save(user);



//       Optional<User> found = Optional.ofNullable(this.userRepository
//               .findByUsername(user.getUsername()));
//       if (found.isEmpty()){
//            this.userRepository.save(user);
//        }
//
//        if (!userRepository.existsByUsername(user.getUsername())){
//            this.userRepository.save(user);
//        }

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
