package com.example.football.models.dto.importDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TownDto implements Serializable {
    @Size(min = 2, message = "Name must be more than or equals two symbols")
    private String name;
    @Positive
    private int population;
    @Size(min = 10)
    private String travelGuide;
}
