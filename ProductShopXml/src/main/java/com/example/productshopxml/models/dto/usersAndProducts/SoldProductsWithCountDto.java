package com.example.productshopxml.models.dto.usersAndProducts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsWithCountDto {

    @XmlAttribute
    private long count;
    @XmlElement(name = "product")
    private List<ProductNameAndPriceDto> products;

    public SoldProductsWithCountDto(List<ProductNameAndPriceDto> products) {
        this.products = products;
        this.count = products.size();
    }
}
