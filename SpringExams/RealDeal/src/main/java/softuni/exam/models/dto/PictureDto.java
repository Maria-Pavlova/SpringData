package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureDto implements Serializable {
    @Size(min = 2, max = 20)
    private String name;
    private LocalDateTime dateAndTime;
    private long car;
}
