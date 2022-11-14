package com.example.jsonproductshop.models.dto.importDto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorySeedDto implements Serializable {
    @NotNull
    @Length(min = 3, max = 15, message = "Invalid name. Must be between 3 and 15 symbols")
    @Expose
    private String name;
}
