package com.example.jsoncardealer.models.dtoExport;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CarExportDto implements Serializable {
    @SerializedName("Id")
    private long id;
    @SerializedName("Make")
    private String make;
    @SerializedName("Model")
    private String model;
    @SerializedName("TravelledDistance")
    private long travelledDistance;
}
