package com.example.productshopxml.models.dto.categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryInfoDto {
    @XmlAttribute
    private String name;
    @XmlElement(name = "product-count")
    private long productCount;
    @XmlElement(name = "average-price")
    private Double averagePrice;
    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;
}
