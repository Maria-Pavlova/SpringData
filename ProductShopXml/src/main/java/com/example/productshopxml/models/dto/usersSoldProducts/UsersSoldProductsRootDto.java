package com.example.productshopxml.models.dto.usersSoldProducts;

import com.example.productshopxml.models.dto.usersSoldProducts.UserNamesDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersSoldProductsRootDto {

    @XmlElement(name = "user")
    private List<UserNamesDto> users;
}
