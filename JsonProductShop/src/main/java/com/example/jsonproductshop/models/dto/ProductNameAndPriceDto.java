package com.example.jsonproductshop.models.dto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductNameAndPriceDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
}
