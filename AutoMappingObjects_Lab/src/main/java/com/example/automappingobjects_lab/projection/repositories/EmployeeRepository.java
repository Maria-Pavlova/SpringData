package com.example.automappingobjects_lab.projection.repositories;

import com.example.automappingobjects_lab.projection.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.subordinates IS NOT EMPTY")
    List<Employee> getManagers();

    List<Employee> findAllByBirthdateBeforeOrderBySalaryDesc(LocalDate beforeYear);


}
