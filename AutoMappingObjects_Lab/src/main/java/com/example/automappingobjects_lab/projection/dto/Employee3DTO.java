package com.example.automappingobjects_lab.projection.dto;

import java.math.BigDecimal;

public class Employee3DTO {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String managerLastName;

//    public Employee3DTO() {
//    }

    public Employee3DTO(String firstName, String lastName, BigDecimal salary, String managerLastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerLastName = managerLastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setManageLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    @Override
    public String toString() {
        return firstName + " "+
                lastName+ " " +
                salary +
                " - Manager: " +
                (managerLastName == null ? "[no manager]" : managerLastName);
    }


    }

