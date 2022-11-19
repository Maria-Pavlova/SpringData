package com.example.productshopxml.models.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @Fetch(FetchMode.JOIN)
    private User buyer;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private User seller;
    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private Set<Category> categories;
}
