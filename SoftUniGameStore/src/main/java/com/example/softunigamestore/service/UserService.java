package com.example.softunigamestore.service;

import com.example.softunigamestore.models.dto.UserLoginDto;
import com.example.softunigamestore.models.dto.UserRegisterDto;
import com.example.softunigamestore.models.entity.User;

import java.util.List;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

    boolean hasLoggedInUser();

    User getLoggedInUser();

    List<String> getOwnedGames();
}

