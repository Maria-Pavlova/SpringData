package com.example.productshopxml.services;

import com.example.productshopxml.models.entities.User;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers(List<User> users);

    User getRandomUser();

    void getUsersSoldProducts() throws IOException, JAXBException;

    void getUsersAndProducts() throws IOException, JAXBException;
}
