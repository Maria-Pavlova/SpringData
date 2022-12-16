package com.example.workshopnextleveltechnologiesmvc.data.dtoExport;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class FinishedProjectDto implements Serializable {
    private String name;
    private String description;
    private BigDecimal payment;

    @Override
    public String toString() {
        return String.format("Project Name: %s%n" +
                        "\tDescription: %s%n" +
                        "\t%.2f",
                this.name, this.description, this.payment);
    }
}
