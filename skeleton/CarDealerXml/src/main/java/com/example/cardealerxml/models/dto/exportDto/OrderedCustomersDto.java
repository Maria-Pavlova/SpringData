package com.example.cardealerxml.models.dto.exportDto;

import com.example.cardealerxml.config.DateTimeAdapter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomersDto implements Serializable {
    @XmlElement
    private long id;
    @XmlElement
    private String name;
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlElement
    private LocalDateTime birthDate;
    @XmlElement
    private boolean isYoungDriver;
}
