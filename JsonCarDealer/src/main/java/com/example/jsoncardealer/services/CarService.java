package com.example.jsoncardealer.services;

import com.example.jsoncardealer.models.entities.Car;

import java.io.IOException;
import java.util.List;

public interface CarService {

    void seedCars(List<Car> cars);

    void getCarsMakeFrom(String makeBy) throws IOException;

    void getCarsWithTheirParts() throws IOException;
}
