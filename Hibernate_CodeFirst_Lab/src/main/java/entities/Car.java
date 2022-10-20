package entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car extends Vehicle{

    private static final String type = "CAR";

    private Integer seats;

    @OneToOne
    @JoinColumn(name = "plate_number_id", referencedColumnName = "id")
    private Plate_number plate_number;


    public Car( String model, BigDecimal price, String fuelType, Integer seats, Plate_number plate_number) {
        super();
        this.seats = seats;
        this.plate_number = plate_number;
    }

    public Car(){}

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
    public Plate_number getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(Plate_number plate_number) {
        this.plate_number = plate_number;
    }
}
