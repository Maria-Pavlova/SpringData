package com.example.workshopnextleveltechnologiesmvc.data.dtoImport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyDto {
    @XmlAttribute
    private String name;
}
