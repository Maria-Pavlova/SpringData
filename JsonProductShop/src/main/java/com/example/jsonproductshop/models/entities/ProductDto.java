package com.example.jsonproductshop.models.entities;

import com.example.jsonproductshop.models.dto.ProductNameAndPriceDto;
import com.example.jsonproductshop.models.dto.SoldProductsWithCountDto;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private User seller;

    @Expose
    private User buyer;




}
