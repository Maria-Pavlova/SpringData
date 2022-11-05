package com.example.usersystem.services;

import com.example.usersystem.models.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> findUserByEmailProvider(String provider);

    List<User> findAllByLastTimeLoggedInBefore(String dateTime);

    int updateUsersIsDeleted(String date);

    int deleteAllByDeletedIsTrue();
}


