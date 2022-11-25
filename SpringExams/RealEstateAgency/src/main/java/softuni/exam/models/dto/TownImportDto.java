package softuni.exam.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class TownImportDto {

    @Size(min = 2)
    private String townName;
    @Positive
    private long population;
}
