package softuni.exam.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class AgentImportDto implements Serializable {
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    private String town;
    @Email(regexp = "^(\\w+@\\w+)(.\\w+){2,}$")
    private String email;
}
