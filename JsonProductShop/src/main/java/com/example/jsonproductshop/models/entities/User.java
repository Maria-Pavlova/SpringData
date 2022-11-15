package com.example.jsonproductshop.models.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity{

    private String firstName;
    @NotNull
    private String lastName;
    private Integer age;

    @ManyToMany
    private Set<User> friends;

    @OneToMany(mappedBy = "seller")
    @Fetch(FetchMode.JOIN)
    @ToString.Exclude
    private List<Product> productsSold;

    @OneToMany(mappedBy = "buyer")
    @Fetch(FetchMode.JOIN)
    private List<Product> productsBought;

    public long getSoldProductsCount(){
        return productsSold.size();
    }

}
