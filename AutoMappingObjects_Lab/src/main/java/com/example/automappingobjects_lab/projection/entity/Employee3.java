package com.example.automappingobjects_lab.projection.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "employees")
public class Employee3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private BigDecimal salary;

    @Column(name = "birth_date")
    private LocalDate birthdate;

    private String address;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Employee3 manager;

    public Employee3() {
    }

    public Employee3(String firstName, String lastName, BigDecimal salary, LocalDate birthdate, String address, Employee3 manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthdate = birthdate;
        this.address = address;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee3 getManager() {
        return manager;
    }

    public void setManager(Employee3 manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Employee3{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", birthdate=" + birthdate +
                ", address='" + address + '\'' +
                ", manager=" + manager +
                '}';
    }
}
