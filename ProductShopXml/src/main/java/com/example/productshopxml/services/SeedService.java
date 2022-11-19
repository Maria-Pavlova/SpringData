package com.example.productshopxml.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SeedService {

    void seedCategories() throws IOException, JAXBException;
    void seedUsers() throws IOException, JAXBException;
    void seedProducts() throws IOException, JAXBException;

    default void seedAll() throws IOException, JAXBException {
        seedCategories();
        seedUsers();
        seedProducts();
    }


}
