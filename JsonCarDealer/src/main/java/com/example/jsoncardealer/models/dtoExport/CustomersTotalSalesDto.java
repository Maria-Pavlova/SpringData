package com.example.jsoncardealer.models.dtoExport;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CustomersTotalSalesDto implements Serializable {

    private String name;

    private Integer boughtCars;

    private BigDecimal spentMoney;
}
