package com.example.automappingobjects_lab.projection.services;

import com.example.automappingobjects_lab.projection.dto.EmployeeDTO;
import com.example.automappingobjects_lab.projection.entity.Employee;
import java.util.List;


public interface EmployeeService {

    Employee findById(long id);

    List<Employee> findByBirthdateBefore(int year);

    List<Employee> findAll();

    List<Employee> getAllManagers();

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Employee deleteEmployeeById(long id);

    long getEmployeesCount();


}
