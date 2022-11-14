package com.example.jsonproductshop.models.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product extends BaseEntity{
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    @ManyToOne
    private User buyer;
    @ManyToOne
    private User seller;
    @ManyToMany
    private Set<Category> categories;
}
