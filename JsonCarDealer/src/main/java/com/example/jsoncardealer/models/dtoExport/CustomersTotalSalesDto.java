package com.example.jsoncardealer.models.dtoExport;

import com.example.jsoncardealer.config.TypeAdapterToDouble;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomersTotalSalesDto implements Serializable {

    private String fullName;

    private long boughtCars;

    @JsonAdapter(TypeAdapterToDouble.class)
    private double spentMoney;
}
