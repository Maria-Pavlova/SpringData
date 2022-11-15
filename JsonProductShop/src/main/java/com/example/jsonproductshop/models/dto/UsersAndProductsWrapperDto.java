package com.example.jsonproductshop.models.dto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsersAndProductsWrapperDto {
    @Expose
    private long usersCount;
    @Expose
    private List<UsersWithSoldProductsDto> users;

    public UsersAndProductsWrapperDto(List<UsersWithSoldProductsDto> users) {
        this.users = users;
        this.usersCount = users.size();
    }
}
