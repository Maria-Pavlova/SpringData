package springdata_accountsystem.services;

import springdata_accountsystem.exceptions.UserNotFoundException;

import springdata_accountsystem.models.User;

import java.math.BigDecimal;

public interface UserService {

    void registerUser(String userName, int age);

    void addAccount(BigDecimal amount, long id) throws  UserNotFoundException;
}
