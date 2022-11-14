package com.example.jsonproductshop.services;

import com.example.jsonproductshop.models.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers(List<User> users);

    User getRandomUser();

    void getUsersSoldProducts() throws IOException;

    void getUsersAndProducts();
}
