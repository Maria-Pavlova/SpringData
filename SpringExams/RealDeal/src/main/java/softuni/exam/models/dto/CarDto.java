package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto implements Serializable {
    @Size(min = 2, max = 20)
    private String make;
    @Size(min = 2, max = 20)
    private String model;
    @Positive
    private int kilometers;
    private LocalDate registeredOn;
}
