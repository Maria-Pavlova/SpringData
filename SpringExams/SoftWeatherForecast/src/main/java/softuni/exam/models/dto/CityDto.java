package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDto implements Serializable {
    @NotNull
    @Size(min = 2, max = 60)
    private String cityName;
    @Size(min = 2)
    private String description;
    @NotNull
    @Min(500)
    private Integer population;

    private long country;
}
