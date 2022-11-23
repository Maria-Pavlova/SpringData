package com.example.cardealerxml.models.dto.exportDto.carAndParts;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartNameAndPriceDto implements Serializable {
   @XmlAttribute
    private String name;
   @XmlAttribute
    private BigDecimal price;
}
