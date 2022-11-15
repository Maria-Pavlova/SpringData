package com.example.jsonproductshop.models.entities;

import com.example.jsonproductshop.models.dto.ProductNameAndPriceDto;
import com.example.jsonproductshop.models.dto.SoldProductsWithCountDto;
import com.example.jsonproductshop.models.dto.UsersAndProductsWrapperDto;
import com.example.jsonproductshop.models.dto.UsersWithSoldProductsDto;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
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
