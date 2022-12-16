package com.example.workshopnextleveltechnologiesmvc.data.dtoExport;

import com.example.workshopnextleveltechnologiesmvc.data.entities.Project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class EmployeesAbove25Dto implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private String projectName;

    @Override
    public String toString() {
        return String.format("Name: %s %s%n\tAge: %d%n\tProject name: %s",
                this.firstName, this.lastName, this.age, this.projectName);
    }
}
