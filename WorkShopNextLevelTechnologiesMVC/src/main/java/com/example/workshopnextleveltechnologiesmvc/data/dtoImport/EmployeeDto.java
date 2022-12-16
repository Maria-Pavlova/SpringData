package com.example.workshopnextleveltechnologiesmvc.data.dtoImport;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@NoArgsConstructor
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeDto implements Serializable {
    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement
    private int age;
    @XmlElement
    private ProjectDto project;
}
