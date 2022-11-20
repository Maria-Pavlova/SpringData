package com.example.jsoncardealer.models.dtoImport;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto implements Serializable {
    private String name;
    private LocalDateTime birthDate;
    private boolean isYoungDriver;
}
