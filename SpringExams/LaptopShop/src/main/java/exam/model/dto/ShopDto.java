package exam.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopDto implements Serializable {
    @XmlElement
    @NotNull
    @Size(min = 4)
    private String name;
    @XmlElement
    @NotNull
    @DecimalMin(value = "20000")
    private BigDecimal income;
    @XmlElement
    @NotNull
    @Size(min = 4)
    private String address;
    @XmlElement(name = "employee-count")
    @Min(1)
    @Max(50)
    private int employeeCount;
    @XmlElement(name = "shop-area")
    @Min(150)
    private int shopArea;
    @XmlElement(name = "town")
    private ShopsTownNameDto town;
}
