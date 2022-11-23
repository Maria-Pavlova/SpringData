package com.example.cardealerxml.services;

import com.example.cardealerxml.models.entities.Car;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CarService {

    void seedCars(List<Car> cars);

    void getCarsMakeFrom(String makeBy) throws IOException, JAXBException;

    void getCarsWithTheirParts() throws IOException, JAXBException;
}
