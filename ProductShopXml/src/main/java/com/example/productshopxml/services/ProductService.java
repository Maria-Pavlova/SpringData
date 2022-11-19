package com.example.productshopxml.services;

import com.example.productshopxml.models.entities.Product;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface ProductService {

    void seedProducts(List<Product> products) throws IOException;

    void getProductsInRange(float from, float to) throws IOException, JAXBException;

    void getCategoriesByProducts() throws IOException, JAXBException;
}
