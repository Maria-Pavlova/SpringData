package com.example.jsoncardealer.models.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class Car extends BaseEntity{

    private String make;

    private String model;

    private long travelledDistance;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private List<Part> parts;
}
