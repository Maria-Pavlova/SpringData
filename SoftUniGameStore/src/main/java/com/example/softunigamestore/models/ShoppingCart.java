package com.example.softunigamestore.models;

import com.example.softunigamestore.models.entity.Game;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany
    private Set<Game> shoppingList;

    public ShoppingCart() {
        this.shoppingList = new HashSet<>();
    }

    public Set<Game> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Set<Game> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void addProduct(Game game) {
        this.shoppingList.add(game);
    }

    public void removeGame(Game game) {
        this.shoppingList.remove(game);
    }
}

