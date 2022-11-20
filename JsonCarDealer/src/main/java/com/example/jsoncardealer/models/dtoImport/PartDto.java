package com.example.jsoncardealer.models.dtoImport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartDto implements Serializable {
    private String name;
    private BigDecimal price;
    private long quantity;
}
