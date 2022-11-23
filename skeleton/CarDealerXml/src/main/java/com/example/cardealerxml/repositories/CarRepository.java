package com.example.cardealerxml.repositories;

import com.example.cardealerxml.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {


    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String makeBy);

    List<Car> findAll();
}
