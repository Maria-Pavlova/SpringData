package com.example.jsoncardealer.models.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity{
    private String name;
    private String birthDate;
    private boolean isYoungDriver;
    @OneToMany(mappedBy = "customer")
    @Fetch(FetchMode.JOIN)
    private Set<Sale> sales;

}
