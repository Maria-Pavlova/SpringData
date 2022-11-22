package com.example.football.models.dto.importDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto implements Serializable {

    @Size(min = 3)
    private String name;

    @Size(min = 3)
    private String stadiumName;

    @Min(value = 1000)
    private int fanBase;

    @Size(min = 10)
    private String history;
}
