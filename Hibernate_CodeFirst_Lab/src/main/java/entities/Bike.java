package entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bikes")
public class Bike extends Vehicle{

    private static final String type = "BIKE";
    public Bike(String model, BigDecimal price, String fuelType) {
        super();
    }

    public Bike(){}

}
