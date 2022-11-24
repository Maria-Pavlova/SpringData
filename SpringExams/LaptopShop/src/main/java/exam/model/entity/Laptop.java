package exam.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "laptops")
public class Laptop extends BaseEntity{
    @Column(name = "mac_address", nullable = false)
    private String macAddress;
    @Column(name = "cpu_speed", nullable = false)
    private double cpuSpeed;
    @Column(name = "ram", nullable = false)
    private int ram;
    @Column(name = "storage", nullable = false)
    private int storage;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "warranty_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WarrantyType warrantyType;
    @ManyToOne
    private Shop shop;

    @Override
    public String toString() {
        return String.format("Laptop - %s\n" +
                "*Cpu speed - %.2f\n" +
                "**Ram - %d\n" +
                "***Storage - %d\n" +
                "****Price - %.2f\n" +
                "#Shop name - %s\n" +
                "##Town - %s\n",
                this.macAddress, this.cpuSpeed, this.ram, this.storage,
                this.price,this.getShop().getName(),this.getShop().getTown().getName());
    }
}
