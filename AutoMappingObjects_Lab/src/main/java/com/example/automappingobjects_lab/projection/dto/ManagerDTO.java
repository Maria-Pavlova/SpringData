package com.example.automappingobjects_lab.projection.dto;

import com.example.automappingobjects_lab.projection.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ManagerDTO {
    private String firstName;
    private String lastName;
    private List<EmployeeDTO> subordinates;


    private int getSubordinatesCount(){
        return subordinates.size();
    }

    @Override
    public String toString() {
        String employees = this.subordinates
                .stream()
                .map(EmployeeDTO::toString)
                .map(s -> "    - " + s)
                .collect(Collectors.joining("\n"));

        return String.format("%s %s | Employees: %d%n%s%n",
                this.firstName, this.lastName, this.subordinates.size(),
                employees);
    }
}
