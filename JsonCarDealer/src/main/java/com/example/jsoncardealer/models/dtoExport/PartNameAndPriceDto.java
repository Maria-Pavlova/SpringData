package com.example.jsoncardealer.models.dtoExport;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PartNameAndPriceDto implements Serializable {
    @SerializedName("Name")
    private String name;
    @SerializedName("Price")
    private BigDecimal price;
}
