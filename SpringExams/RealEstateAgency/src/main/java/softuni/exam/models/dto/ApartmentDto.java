package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import softuni.exam.models.entity.ApartmentType;

import javax.naming.Name;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentDto implements Serializable {

    @NotNull
    @XmlElement
    private ApartmentType apartmentType;
    @NotNull
    @DecimalMin(value = "40.00")
    @XmlElement(name = "area")
    private double area;
    @NotNull
    @XmlElement(name = "town")
    private String townName;
}
