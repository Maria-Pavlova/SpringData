package com.example.automappingobjects_lab.projection.services;

import com.example.automappingobjects_lab.projection.dto.Employee3DTO;
import com.example.automappingobjects_lab.projection.entity.Employee3;

import java.util.List;
import java.util.Optional;

public interface Employee3Service {

    Optional<Employee3> findOneById(int id);

    void save(Employee3 employee3);

    List<Employee3DTO> findByBirthdateBefore(int year);

    List<Employee3> findAll();
}
