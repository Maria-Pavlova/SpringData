package com.example.football.models.dto.importDto;

import com.example.football.config.LocalDateTypeAdapter;
import com.example.football.models.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerDto implements Serializable {

    @NotNull
    @Size(min = 2)
    @XmlElement(name = "first-name")
    private String firstName;

    @NotNull
    @Size(min = 2)
    @XmlElement(name = "last-name")
    private String lastName;


    @Email
    @XmlElement
    private String email;

    @NotNull
    @XmlJavaTypeAdapter(LocalDateTypeAdapter.class)
    @XmlElement(name = "birth-date")
    private LocalDate birthDate;

    @NotNull
    @XmlElement
    private Position position;

    @NotNull
    @XmlElement
    private TownNameDto town;

    @NotNull
    @XmlElement
    private TeamNameDto team;

    @NotNull
    @XmlElement
    private StatIdDto stat;


}
