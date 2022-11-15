package com.example.softunigamestore.service;

public interface OrderService {

    void addItem(String gameName);

    void removeItem(String gameName);

    void buyItem();
}
