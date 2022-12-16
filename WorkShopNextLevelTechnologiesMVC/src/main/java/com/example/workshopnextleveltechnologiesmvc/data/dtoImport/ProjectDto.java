package com.example.workshopnextleveltechnologiesmvc.data.dtoImport;

import com.example.workshopnextleveltechnologiesmvc.config.LocalDateXml;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectDto implements Serializable {
    @NotNull
    @XmlElement
    private String name;
    @NotNull
    @XmlElement
    private String description;
    @XmlElement(name = "is-finished")
    private Boolean isFinished;
    @NotNull
    @XmlElement
    private BigDecimal payment;
    @XmlElement(name = "start-date")
    @XmlJavaTypeAdapter(LocalDateXml.class)
    private LocalDate startDate;
    @NotNull
    @XmlElement
    private CompanyNameDto company;
}
