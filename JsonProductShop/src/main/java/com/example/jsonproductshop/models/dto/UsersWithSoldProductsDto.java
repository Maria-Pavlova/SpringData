package com.example.jsonproductshop.models.dto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersWithSoldProductsDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    private SoldProductsWithCountDto soldProducts;
}
