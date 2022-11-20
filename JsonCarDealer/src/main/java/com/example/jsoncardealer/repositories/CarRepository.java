package com.example.jsoncardealer.repositories;

import com.example.jsoncardealer.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {


    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String makeBy);

    List<Car> findAll();
}
