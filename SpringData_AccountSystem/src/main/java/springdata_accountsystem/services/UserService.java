package springdata_accountsystem.services;

import springdata_accountsystem.exceptions.UserNotFoundException;

import springdata_accountsystem.models.User;

import java.math.BigDecimal;

public interface UserService {

    void registerUser(User user);

    void addAccount(BigDecimal amount, long id) throws  UserNotFoundException;
}
