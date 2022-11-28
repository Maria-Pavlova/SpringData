package softuni.exam.models.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import softuni.exam.config.LocalDateTimeAdapter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketDto {
    @Size(min = 2)
    @XmlElement(name = "serial-number")
    private String serialNumber;
    @Positive
    @XmlElement
    private BigDecimal price;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement(name = "take-off")
    private LocalDateTime takeoff;
    @XmlElement(name = "from-town")
    private FromTownNameDto fromTown;
    @XmlElement(name = "to-town")
    private ToTownNameDto toTown;
    @XmlElement()
    private PassengerEmailDto passenger;
    @XmlElement
    private PlaneNumberDto plane;
}
