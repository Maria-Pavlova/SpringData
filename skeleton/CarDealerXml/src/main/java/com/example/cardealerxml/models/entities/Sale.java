package com.example.cardealerxml.models.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity{
    @OneToOne
    private Car car;
    @ManyToOne
    private Customer customer;
    private Double discountPercentage;
}
