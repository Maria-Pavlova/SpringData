package com.example.jsonproductshop.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SeedService {

    void seedCategories() throws IOException;
    void seedUsers() throws IOException, JAXBException;
    void seedProducts() throws IOException;

    default void seedAll() throws IOException {
        seedCategories();
        seedUsers();
        seedProducts();
    }


}
