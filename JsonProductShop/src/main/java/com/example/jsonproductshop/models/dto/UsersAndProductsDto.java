package com.example.jsonproductshop.models.dto;

import com.google.gson.annotations.Expose;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersAndProductsDto implements Serializable {
    @Expose
    private long usersCount;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;
    @Expose
    private List<SoldProductsCountDto> soldProducts;




//    private List<UsersWithSoldProducts> users;
//
//    private long getUsersCount(){
//        return users.size();
//    }
}
