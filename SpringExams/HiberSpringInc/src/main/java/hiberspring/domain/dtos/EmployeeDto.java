package hiberspring.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeDto implements Serializable {
    @NotNull
    @XmlAttribute(name = "first-name")
    private String firstName;
    @NotNull
    @XmlAttribute(name = "last-name")
    private String lastName;
    @NotNull
    @XmlAttribute
    private String position;
    @NotNull
    @XmlElement
    private String card;
    @NotNull
    @XmlElement
    private String branch;
}
