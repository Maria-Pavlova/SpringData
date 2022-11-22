package com.example.football.models.dto.importDto;
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
@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayersRootDto {

    @XmlElement(name = "player")
    private List<PlayerDto> players;
}
