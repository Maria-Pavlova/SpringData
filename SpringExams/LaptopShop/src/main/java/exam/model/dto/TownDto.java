package exam.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownDto implements Serializable {
    @XmlElement
    @NotNull
    @Size(min = 2)
    private String name;
    @XmlElement
    @NotNull
    @Positive
    private long population;
    @XmlElement(name = "travel-guide")
    @NotNull
    @Size(min = 10)
    private String travelGuide;
}
