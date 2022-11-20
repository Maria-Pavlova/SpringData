package com.example.jsoncardealer.models.dtoImport;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarImportDto {

    private String make;

    private String model;

    private long travelledDistance;

}
