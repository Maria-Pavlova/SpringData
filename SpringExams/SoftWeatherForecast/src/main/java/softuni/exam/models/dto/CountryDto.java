package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto implements Serializable {
    @Size(min = 2, max = 60)
    @NotNull
    private String countryName;
    @Size(min = 2, max = 20)
    @NotNull
    private String currency;
}
