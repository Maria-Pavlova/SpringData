package com.example.jsoncardealer.models.dtoExport;

import com.example.jsoncardealer.models.dtoImport.CarDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class CarAndPartDto implements Serializable {
    private CarDto car;
    private List<PartNameAndPriceDto> parts;
}
