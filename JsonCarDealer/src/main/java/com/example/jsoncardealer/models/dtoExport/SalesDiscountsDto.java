package com.example.jsoncardealer.models.dtoExport;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SalesDiscountsDto implements Serializable {
    private CarDto car;
    private String customerName;
    @SerializedName("Discount")
    private Double discountPercentage;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;
}
