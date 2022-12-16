package com.example.workshopnextleveltechnologiesmvc.data.dtoImport;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectsRootDto {
    @XmlElement(name = "project")
    private List<ProjectDto> projects;
}
