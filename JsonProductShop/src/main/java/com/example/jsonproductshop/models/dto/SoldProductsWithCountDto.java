package com.example.jsonproductshop.models.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SoldProductsWithCountDto {
@Expose
    private long count;
@Expose
    private List<ProductNameAndPriceDto> products;

    public SoldProductsWithCountDto(List<ProductNameAndPriceDto> products) {
        this.products = products;
        this.count = products.size();
    }
}
