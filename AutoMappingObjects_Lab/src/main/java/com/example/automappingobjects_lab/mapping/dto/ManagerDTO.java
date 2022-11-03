package com.example.automappingobjects_lab.mapping.dto;

import java.util.Set;
import java.util.stream.Collectors;

public class ManagerDTO {
    private String firstName;
    private String lastName;
    private Set<EmployeeDTO> workers;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setWorkers(Set<EmployeeDTO> workers) {
        this.workers = workers;
    }

    @Override
    public String toString() {
        String employees = this.workers
                .stream()
                .map(EmployeeDTO::toString)
                .map(s -> "    - " + s)
                .collect(Collectors.joining("\n"));

        return String.format("%s %s | Employees: %d%n%s%n",
                this.firstName, this.lastName, this.workers.size(),
                employees);
    }
}
