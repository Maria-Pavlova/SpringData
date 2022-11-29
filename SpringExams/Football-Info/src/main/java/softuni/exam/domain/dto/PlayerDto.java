package softuni.exam.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import softuni.exam.domain.entities.Position;

import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto implements Serializable {
    @NotNull
    private String firstName;
    @NotNull
    @Size(min = 3, max = 15)
    private String lastName;
    @NotNull
    @Min(1)
    @Max(99)
    private int number;
    @NotNull
    @PositiveOrZero
    private BigDecimal salary;
    @NotNull
    private Position position;
    @NotNull
    private PictureUrlDto picture;
    @NotNull
    private PlayersTeamDto team;
}
