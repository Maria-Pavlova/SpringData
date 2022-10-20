package entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Drivers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String full_name;

    @ManyToMany
    @JoinTable(name = "drivers_trucks",
            joinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "truck_id", referencedColumnName = "id"))

    private Set<Truck> trucks;

    public Drivers(String full_name) {
        this.full_name = full_name;
        this.trucks = new HashSet<>();
    }

    public Drivers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Set<Truck> getTrucks() {
        return Collections.unmodifiableSet(trucks);
    }

    public void addTruck(Truck truck) {
        trucks.add(truck);
    }
}
