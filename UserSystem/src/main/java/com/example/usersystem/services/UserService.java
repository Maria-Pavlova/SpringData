package com.example.usersystem.services;

import com.example.usersystem.models.User;

import java.util.List;

public interface UserService {
    void seedUser();

    List<User> findUserByEmailProvider(String provider);
}
