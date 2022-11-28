package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import softuni.exam.models.entities.Rating;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerDto implements Serializable {
    @Size(min = 2, max = 20)
    @XmlElement(name = "first-name")
    private String firstName;
    @Size(min = 2, max = 20)
    @XmlElement(name = "last-name")
    private String lastName;
    @NotNull
    @Email
    @XmlElement
    private String email;
    @NotNull
    @XmlElement
    private Rating rating;
    @NotNull
    @XmlElement
    private String town;
}
