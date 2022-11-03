package com.example.usersystem.services;

import com.example.usersystem.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    void seedUser();

    List<User> findUserByEmailProvider(String provider);

    List<User> findAllByLastTimeLoggedInBefore(String dateTime);

    int updateUsersIsDeleted(String date);

    int deleteAllByDeletedIsTrue();
}


