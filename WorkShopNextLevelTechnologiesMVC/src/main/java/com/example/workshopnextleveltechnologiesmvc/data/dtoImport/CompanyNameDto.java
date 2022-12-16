package com.example.workshopnextleveltechnologiesmvc.data.dtoImport;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyNameDto {
    @XmlAttribute
    private String name;
}
