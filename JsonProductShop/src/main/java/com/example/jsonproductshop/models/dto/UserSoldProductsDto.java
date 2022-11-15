package com.example.jsonproductshop.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSoldProductsDto implements Serializable {
    @Expose
    private String firstName;
    @NotNull
    @Expose
    private String lastName;
    @Expose
    @SerializedName("soldProducts")
    private List<SoldProductDto> productsBought;
}
