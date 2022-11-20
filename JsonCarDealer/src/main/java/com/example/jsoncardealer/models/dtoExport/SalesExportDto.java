package com.example.jsoncardealer.models.dtoExport;

import com.example.jsoncardealer.models.dtoImport.CarDto;
import com.example.jsoncardealer.models.dtoImport.CustomerDto;
import com.example.jsoncardealer.models.entities.Car;
import com.example.jsoncardealer.models.entities.Customer;
import lombok.*;

import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesExportDto implements Serializable {
    private long id;
    private CarExportDto car;
   // private CustomerDto customer;
    private Double discountPercentage;




}
