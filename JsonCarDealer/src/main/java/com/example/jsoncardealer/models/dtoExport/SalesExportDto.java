package com.example.jsoncardealer.models.dtoExport;

import com.example.jsoncardealer.models.dtoImport.CustomerDto;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesExportDto implements Serializable {
    private long id;
    private CarExportDto car;
    private CustomerDto customer;
    private Double discountPercentage;




}
