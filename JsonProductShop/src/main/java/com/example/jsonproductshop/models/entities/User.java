package com.example.jsonproductshop.models.entities;

import lombok.*;

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
    private int age;
    @ManyToMany
    private Set<User> friends;
    @OneToMany(mappedBy = "seller",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Product> productsSold;
    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private List<Product> productsBought;

    public long getSoldProductsCount(){
        return productsSold.size();
    }

}
