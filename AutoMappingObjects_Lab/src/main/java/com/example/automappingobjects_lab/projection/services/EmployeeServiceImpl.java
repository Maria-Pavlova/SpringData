package com.example.automappingobjects_lab.projection.services;

import com.example.automappingobjects_lab.projection.entity.Employee;
import com.example.automappingobjects_lab.projection.exceptions.NonExistingEntityException;
import com.example.automappingobjects_lab.projection.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findById(long id) {
        return this.employeeRepository.findById(id).orElseThrow(
                () -> new NonExistingEntityException(
                        String.format("Employee with id = %s does not exists.", id))
        );

    }

    @Override
    public List<Employee> findByBirthdateBefore(int year) {
        LocalDate beforeYear = LocalDate.of(year, 1, 1);
        return this.employeeRepository.findAllByBirthdateBeforeOrderBySalaryDesc(beforeYear);
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllManagers() {
        return employeeRepository.getManagers();
    }

    @Override
    @Transactional
    public Employee addEmployee(Employee employee) {
        employee.setId(null);
        if (employee.getManager() != null){
            employee.getManager().getSubordinates().add(employee);
        }
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        Employee existing = findById(employee.getId());
        Employee updated = employeeRepository.save(employee);
        if (existing.getManager() != null && !existing.getManager().equals(employee.getManager())) {
            existing.getManager().getSubordinates().remove(existing);
        }
        if (updated.getManager() != null && !updated.getManager().equals(existing.getManager())){
            updated.getManager().getSubordinates().add(updated);
        }
        return updated;
    }

    @Override
    @Transactional
    public Employee deleteEmployeeById(long id) {
        Employee removed = findById(id);
        if (removed.getManager() != null){
            removed.getManager().getSubordinates().remove(removed);
        }
        employeeRepository.deleteById(id);
        return removed;
    }

    @Override
    public long getEmployeesCount() {
        return employeeRepository.count();
    }
}
