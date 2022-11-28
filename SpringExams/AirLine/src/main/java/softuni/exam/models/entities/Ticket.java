package softuni.exam.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity{
    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(name = "take_off")
    private LocalDateTime takeoff;
    @ManyToOne
    private Town fromTown;
    @ManyToOne
    private Town toTown;
    @ManyToOne(fetch = FetchType.EAGER)
    private Passenger passenger;
    @ManyToOne
    private Plane plane;

}
