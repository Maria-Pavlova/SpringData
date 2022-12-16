package com.example.workshopnextleveltechnologiesmvc.data.dtoImport;

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
@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyRootDto {

    @XmlElement(name = "company")
    private List<CompanyDto> companies;
}
