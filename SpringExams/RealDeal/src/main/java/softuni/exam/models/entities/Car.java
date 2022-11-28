package softuni.exam.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity{


    private String make;
    private String model;
    private int kilometers;
    @Column(name = "registered_on")
    private LocalDate registeredOn;
    @OneToMany(mappedBy = "car", targetEntity = Picture.class)
    @Fetch(FetchMode.JOIN)
    private Set<Picture> pictures;

    @Override
    public String toString() {
        return String.format("Car make - %s, model - %s\n" +
                "\tKilometers - %d\n" +
                "\tRegistered on - %s\n" +
                "\tNumber of pictures - %d\n",
                this.make, this.model, this.kilometers, this.registeredOn, this.pictures.size()
        );
    }
}
