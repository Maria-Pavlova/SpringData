package com.example.productshopxml.models.dto.usersSoldProducts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserNamesDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products" )
    private SoldProductsRootDto productsBought;

    public UserNamesDto(SoldProductsRootDto productsBought) {
        this.productsBought = productsBought;
    }
}
