package com.example.jsonproductshop.services;

import com.example.jsonproductshop.models.entities.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    void seedProducts(List<Product> products) throws IOException;

    void getProductsInRange(float from, float to) throws IOException;

    void getCategoriesByProducts() throws IOException;
}
