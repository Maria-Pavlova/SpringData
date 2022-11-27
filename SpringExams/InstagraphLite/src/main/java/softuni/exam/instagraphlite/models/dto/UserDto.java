package softuni.exam.instagraphlite.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    @NotNull
    @Size(min = 2, max = 18)
    private String username;
    @NotNull
    @Size(min = 4)
    private String password;
    @NotNull
    private String profilePicture;
}
