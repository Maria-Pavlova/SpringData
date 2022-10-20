package com.example.usersystem;
import com.example.usersystem.models.User;
import com.example.usersystem.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

       // userService.seedUser();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email provider");
        String provider = scanner.nextLine();

        List<User> users = userService.findUserByEmailProvider(provider);
        if (users.isEmpty()){
            System.out.println("No users found with email domain" + provider);
        }
        users.forEach(u->
                System.out.printf("--- User name: %s -> Email: %s%n", u.getUsername(), u.getEmail()));

    }
}
