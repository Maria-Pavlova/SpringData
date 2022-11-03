package com.example.automappingobjects_lab.mapping.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Employee {
    private String firstname;
    private String lastname;
    private BigDecimal salary;
    private LocalDate localDate;
    private Address address;
    private boolean onVacation;
    private Employee manager;
    private Set<Employee> workers;

    public Employee(String firstname, String lastname, BigDecimal salary,
                    LocalDate localDate, Address address, boolean onVacation) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.salary = salary;
        this.localDate = localDate;
        this.address = address;
        this.onVacation = onVacation;
        this.workers = new HashSet<>();
    }

    public void addWorker(Employee worker ){
        this.workers.add(worker);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isOnVacation() {
        return onVacation;
    }

    public void setOnVacation(boolean onVacation) {
        this.onVacation = onVacation;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Employee> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Employee> workers) {
        this.workers = workers;
    }
}
