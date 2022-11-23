package com.example.cardealerxml.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SeedService {
    void seedSuppliers() throws IOException, JAXBException;
    void seedParts() throws IOException, JAXBException;
    void seedCars() throws IOException, JAXBException;
    void seedCustomers() throws IOException, JAXBException;


    default void seedAllData() throws IOException, JAXBException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();

    }

}
