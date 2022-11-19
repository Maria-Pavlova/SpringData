package com.example.productshopxml.models.entities;

import com.example.productshopxml.models.dto.usersAndProducts.ProductNameAndPriceDto;
import com.example.productshopxml.models.dto.usersAndProducts.SoldProductsWithCountDto;
import com.example.productshopxml.models.dto.usersAndProducts.UsersWithSoldProductsDto;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    private Set<ProductDto> productsSold;
    @Expose
    private Set<ProductDto> productsBought;
    @Expose
    private Set<UserDto> friends;

    public String getFullName(){
        return firstName + " " + lastName;
    }

//    public UsersAndProductsWrapperDto toUsersAndProductsWrapperDto(){
//        return new UsersAndProductsWrapperDto();
//    }

    public UsersWithSoldProductsDto toUsersWithSoldProductsDto(){
        return new UsersWithSoldProductsDto(firstName,lastName,age, toSoldProductsWithCountDto());
    }
    public SoldProductsWithCountDto toSoldProductsWithCountDto(){
        return new SoldProductsWithCountDto(productsSold
                .stream()
                .map(this::toProductNameAndPriceDto)
                .collect(Collectors.toList()));
    }
    public ProductNameAndPriceDto toProductNameAndPriceDto(ProductDto productDto){
        return new ProductNameAndPriceDto(productDto.getName(), productDto.getPrice());
    }
}
