package com.example.cardealerxml.models.dto.importDto;

import com.example.cardealerxml.config.DateTimeAdapter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImportDto implements Serializable {
    @XmlAttribute
    private String name;

    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlElement(name = "birth-date")
    private LocalDateTime birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;
}
