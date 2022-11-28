package softuni.exam.models.dto.plane;

import lombok.*;
import softuni.exam.models.dto.plane.PlaneDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneRootDto {
    @XmlElement(name = "plane")
    private List<PlaneDto> planes;
}
