package com.example.usersystem;
import com.example.usersystem.models.User;
import com.example.usersystem.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
   private final UserService userService;

    public CommandLineRunnerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

      //  userService.seedUser();

     //   findUsersByEmailProvider();

        // Ensure that in DB there is a user with date before you need
//        int count = userService.updateUsersIsDeleted("2022-10-25 10:00:00.000000");
//        System.out.println(count + "users have been set as deleted");

        int countDeleted = userService.deleteAllByDeletedIsTrue();
        System.out.println(countDeleted + " users was deleted");


    }

    private void findUsersByEmailProvider() {
        System.out.println("Enter email provider");
        String provider = new Scanner(System.in).nextLine();

        List<User> users = userService.findUserByEmailProvider(provider);
        if (users.isEmpty()){
            System.out.println("No users found with email domain" + provider);
        }
        users.forEach(u->
                System.out.printf("--- User name: %s -> Email: %s%n", u.getUsername(), u.getEmail()));
    }
}
