package com.example.automappingobjects_lab.projection.services;

import com.example.automappingobjects_lab.projection.dto.Employee3DTO;
import com.example.automappingobjects_lab.projection.entity.Employee3;
import com.example.automappingobjects_lab.projection.repositories.Employee3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class Employee3ServiceImpl implements Employee3Service {

    private final Employee3Repository employee3Repository;

    public Employee3ServiceImpl(Employee3Repository employee3Repository) {
        this.employee3Repository = employee3Repository;
    }

    @Override
    public Optional<Employee3> findOneById(int id) {
        Employee3 employee3One = this.employee3Repository.findById(id).get();
        return Optional.ofNullable(employee3One);
    }

    @Override
    public void save(Employee3 employee3) {
        this.employee3Repository.save(employee3);
    }

    @Override
    public List<Employee3DTO> findByBirthdateBefore(int year) {
        LocalDate beforeYear = LocalDate.of(year, 1, 1);
        return this.employee3Repository.findAllByBirthdateBeforeOrderBySalaryDesc(beforeYear);

    }

    @Override
    public List<Employee3> findAll() {
        return this.employee3Repository.findAll();
    }
}
