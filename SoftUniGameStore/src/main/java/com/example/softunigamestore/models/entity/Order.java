package com.example.softunigamestore.models.entity;

import com.example.softunigamestore.models.ShoppingCart;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "orders")
public class Order extends BaseEntity{

    @ManyToOne(optional = false)
    private User buyer;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> products;

    @Transient
    @OneToOne
    private ShoppingCart shoppingCart;

    public Order() {
        this.products = new HashSet<>();
    }

    public Order(User buyer, ShoppingCart shoppingCart) {
        this();
        this.buyer = buyer;
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Set<Game> getProducts() {
        return products;
    }

    public void setProducts(Set<Game> products) {
        this.products = products;
    }

    public void addProduct(Game game){
        this.products.add(game);
    }

    public void deleteProduct(Game game){
        this.products.remove(game);
    }
}
