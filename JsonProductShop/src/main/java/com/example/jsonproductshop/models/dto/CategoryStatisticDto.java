package com.example.jsonproductshop.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CategoryStatisticDto {
    @Expose
    @SerializedName("category")
    private String name;
    @Expose
    private long productsCount;
    @Expose
    private double averagePrice;
    @Expose
    private BigDecimal totalRevenue;
}
