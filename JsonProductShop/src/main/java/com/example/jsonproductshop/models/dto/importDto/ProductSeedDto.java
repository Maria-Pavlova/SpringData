package com.example.jsonproductshop.models.dto.importDto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSeedDto implements Serializable {
    @Expose
    @NotNull
    @Length(min = 3, message = "Name of the product must be at least 3 symbols")
    private String name;
    @Expose
    @NotNull
    @Positive
    private BigDecimal price;
}
