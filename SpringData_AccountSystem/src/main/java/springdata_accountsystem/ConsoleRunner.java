package springdata_accountsystem;

import springdata_accountsystem.exceptions.InsufficientFoundException;
import springdata_accountsystem.models.User;
import springdata_accountsystem.services.AccountService;
import springdata_accountsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {


    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
//        User user1 = new User("Ani", 25);
//        userService.registerUser(user1);
//
//        User user2 = new User("Bill", 22);
//        userService.registerUser(user2);
//
//        this.userService.addAccount(BigDecimal.valueOf(1000), 1);
//        this.userService.addAccount(BigDecimal.valueOf(500), 2);
//
//
//        try {
//            this.accountService.withdrawMoney(new BigDecimal(150),1L);
//            this.accountService.transferMoney(new BigDecimal(150),2L);
//
//        } catch (InsufficientFoundException e) {
//            e.printStackTrace();
//        }


        this.accountService.transferBetweenAccounts(2L, 1L, new BigDecimal(150));
    }
}
