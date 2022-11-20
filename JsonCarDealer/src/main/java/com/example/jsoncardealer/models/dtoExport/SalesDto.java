package com.example.jsoncardealer.models.dtoExport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesDto {
    private long id;
    private CarExportDto car;
    private Double discountPercentage;
}
