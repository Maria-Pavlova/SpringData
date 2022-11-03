package com.example.automappingobjects_lab.projection.repositories;

import com.example.automappingobjects_lab.projection.dto.Employee3DTO;
import com.example.automappingobjects_lab.projection.entity.Employee3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface Employee3Repository extends JpaRepository<Employee3,Integer> {


    List<Employee3DTO> findAllByBirthdateBeforeOrderBySalaryDesc(LocalDate beforeYear);





}
