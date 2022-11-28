package softuni.exam.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "planes")
public class Plane extends BaseEntity{
    @Column(name = "register_number",nullable = false,unique = true)
    private String registerNumber;
    @Column
    private int capacity;
    @Column
    private String airline;
}
