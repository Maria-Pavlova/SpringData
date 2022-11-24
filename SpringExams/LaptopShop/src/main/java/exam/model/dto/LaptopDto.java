package exam.model.dto;

import exam.model.entity.WarrantyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaptopDto implements Serializable {
    @NotNull
    @Size(min = 8)
    private String macAddress;
    @NotNull
    @Positive
    private double cpuSpeed;
    @NotNull
    @Min(8)
    @Max(128)
    private int ram;
    @NotNull
    @Min(128)
    @Max(1024)
    private int storage;
    @NotNull
    @Size(min = 10)
    private String description;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    private WarrantyType warrantyType;
    @NotNull
    private ShopNameDto shop;
}
