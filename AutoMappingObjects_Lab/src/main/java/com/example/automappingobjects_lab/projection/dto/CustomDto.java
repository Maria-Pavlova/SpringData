package com.example.automappingobjects_lab.projection.dto;

public class CustomDto {
    private String firstName;
    private String  lastName;
    private int managerLastNameLength;

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

    public int getManagerLastNameLength() {
        return managerLastNameLength;
    }

    public void setManagerLastNameLength(int managerLastNameLength) {
        this.managerLastNameLength = managerLastNameLength;
    }
}
