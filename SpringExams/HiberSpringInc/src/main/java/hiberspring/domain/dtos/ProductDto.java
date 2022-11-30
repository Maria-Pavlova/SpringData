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
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDto implements Serializable {
    @XmlAttribute
    @NotNull
    private String name;
    @XmlAttribute
    @NotNull
    private Integer clients;
    @XmlElement(name = "branch")
    @NotNull
    private String branch;
}
