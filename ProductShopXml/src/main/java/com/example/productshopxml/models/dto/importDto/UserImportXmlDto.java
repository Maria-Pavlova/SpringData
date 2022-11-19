package com.example.productshopxml.models.dto.importDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImportXmlDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @NotNull
    @Length(min = 3, message = "Name must be at least 3 symbols")
    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute
    private Integer age;
}
