package com.example.automappingobjects_lab.projection.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String managerLastName;




    @Override
    public String toString() {
        return firstName + " "+
                lastName+ " " +
                salary +
                " - Manager: " +
                (managerLastName == null ? "[no manager]" : managerLastName);
    }


    }

