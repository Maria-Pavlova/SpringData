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
public class UserSeedDto implements Serializable {
    @Expose
    private String firstName;
    @Expose
    @NotNull
    @Length(min = 3, message = "The name must be at least 3 symbols")
    private String lastName;
    @Expose
    private Integer age;
}
