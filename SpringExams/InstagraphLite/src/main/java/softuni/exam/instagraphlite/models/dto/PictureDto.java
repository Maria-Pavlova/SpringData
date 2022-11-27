package softuni.exam.instagraphlite.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureDto implements Serializable {
    @NotNull
    private String path;
    @NotNull
    @DecimalMin(value = "500")
    @DecimalMax(value = "60000")
    private double size;
}
