package softuni.exam.models.dto.passenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Positive
    private int age;
    private String phoneNumber;
    @Email(regexp = "^(\\w+@\\w+)(.\\w+){2,}$")
    private String email;
    private String town;
}
