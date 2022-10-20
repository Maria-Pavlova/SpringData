package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicle{
    private final static String type = "TRUCK";
    private Double loadCapacity;

    @ManyToMany(mappedBy = "trucks", targetEntity = Drivers.class
    ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Truck> trucks;

    public Truck(String model, BigDecimal price, String fuelType, Double loadCapacity) {
        super();
        this.loadCapacity = loadCapacity;
        this.trucks = new HashSet<>();
    }

    public Truck(){}

    public Double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(Double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public Set<Truck> getTrucks() {
        return Collections.unmodifiableSet(this.trucks);
    }

    public void addTruck(Truck truck){
        this.trucks.add(truck);
    }
}
