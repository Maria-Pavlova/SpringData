package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import softuni.exam.config.LocalTimeXml;
import softuni.exam.models.entity.DayOfWeek;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastDto implements Serializable {
    @NotNull
    @XmlElement(name = "day_of_week")
    private DayOfWeek dayOfWeek;
    @NotNull
    @Min(-20)
    @Max(60)
    @XmlElement(name = "max_temperature")
    private double maxTemperature;
    @NotNull
    @Min(-50)
    @Max(40)
    @XmlElement(name = "min_temperature")
    private double minTemperature;
    @NotNull
    @XmlElement
    @XmlJavaTypeAdapter(LocalTimeXml.class)
    private LocalTime sunrise;
    @NotNull
    @XmlElement
    @XmlJavaTypeAdapter(LocalTimeXml.class)
    private LocalTime sunset;
    @XmlElement
    private long city;
}
