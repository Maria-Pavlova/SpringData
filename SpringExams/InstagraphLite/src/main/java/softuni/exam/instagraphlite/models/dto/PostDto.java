package softuni.exam.instagraphlite.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostDto implements Serializable {
    @XmlElement
    @NotNull
    @Size(min = 21)
    private String caption;
    @XmlElement
    @NotNull
    private UserNameDto user;
    @XmlElement
    @NotNull
    private PicturePathDto picture;
}
