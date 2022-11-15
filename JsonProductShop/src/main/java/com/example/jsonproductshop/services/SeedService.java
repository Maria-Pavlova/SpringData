package com.example.jsonproductshop.services;

import java.io.IOException;

public interface SeedService {

    void seedCategories() throws IOException;
    void seedUsers() throws IOException;
    void seedProducts() throws IOException;

    default void seedAll() throws IOException {
        seedCategories();
        seedUsers();
        seedProducts();
    }


}
