package entities;
import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "planes")
public class Plane extends Vehicle{

    private static final String type = "PLANE";
    @Column(name = "passenger_capacity")
    private Integer passengerCapacity;
    private String airline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Companies companies;

    public Plane(String model, BigDecimal price, String fuelType, Integer passengerCapacity, String airline, Companies companies) {
        super();
        this.passengerCapacity = passengerCapacity;
        this.airline = airline;
        this.companies = companies;
    }
    public Plane(){}

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Companies getCompanies() {
        return companies;
    }

    public void setCompanies(Companies companies) {
        this.companies = companies;
    }
}
