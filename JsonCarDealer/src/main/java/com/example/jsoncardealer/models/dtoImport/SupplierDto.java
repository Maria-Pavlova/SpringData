package com.example.jsoncardealer.models.dtoImport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto implements Serializable {
    private String name;
    private boolean isImporter;
}
