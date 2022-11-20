package com.example.jsoncardealer.models.dtoExport;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerExportDto implements Serializable {
    private long id;
    private String name;
    private String birthDate;
    private boolean isYoungDriver;
    private List<SalesExportDto> sales;


}
