package softuni.exam.models.dto.town;

import lombok.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TownDto {

    @Size(min = 2)
    private String name;
    @Positive
    private int population;

    private String guide;
}
