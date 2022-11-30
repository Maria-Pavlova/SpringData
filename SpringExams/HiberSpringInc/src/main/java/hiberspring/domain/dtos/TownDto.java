package hiberspring.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TownDto implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private Integer population;
}
