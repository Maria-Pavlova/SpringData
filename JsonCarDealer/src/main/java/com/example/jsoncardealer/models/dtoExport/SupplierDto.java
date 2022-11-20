package com.example.jsoncardealer.models.dtoExport;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SupplierDto implements Serializable {
    private long Id;
    private String Name;
    private int partsCount;
}
