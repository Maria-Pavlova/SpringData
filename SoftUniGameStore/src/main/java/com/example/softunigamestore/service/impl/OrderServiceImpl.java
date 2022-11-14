package com.example.softunigamestore.service.impl;

import com.example.softunigamestore.models.ShoppingCart;
import com.example.softunigamestore.models.entity.Game;
import com.example.softunigamestore.models.entity.Order;
import com.example.softunigamestore.models.entity.User;
import com.example.softunigamestore.repositories.GameRepository;
import com.example.softunigamestore.repositories.OrderRepository;
import com.example.softunigamestore.repositories.UserRepository;
import com.example.softunigamestore.service.OrderService;
import com.example.softunigamestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final GameRepository gameRepository;
    private final UserService userService;
    private ShoppingCart shoppingCart;
    private UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, GameRepository gameRepository, UserService userService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.shoppingCart = new ShoppingCart();

    }


    @Transactional
    @Override
    public void addItem(String gameName) {
        Game game = gameRepository.findByTitle(gameName);
        if (userService.getOwnedGames().contains(gameName)) {
            throw new RuntimeException("Impossible operation. User owns this game");
        }

        if (!shoppingCart.getShoppingList().contains(game)) {
            shoppingCart.addProduct(game);
            System.out.println(gameName + " added to cart.");
        }
    }

    @Transactional
    @Override
    public void removeItem(String gameName) {
        Game game = gameRepository.findByTitle(gameName);
        if (shoppingCart.getShoppingList().contains(game)) {
            shoppingCart.removeGame(game);
            System.out.println(gameName + " removed from cart.");
            //todo
        }
    }

    @Override
    public void orderItem() {
        Order order = new Order();
    }

    @Transactional
    @Override
    public void buyItem() {

        Set<Game> shoppingList = shoppingCart.getShoppingList();
        User buyer = userService.getLoggedInUser();
          Order order = new Order(buyer,shoppingCart);
        Set<Game> products = order.getProducts();
        products.addAll(shoppingList);
        orderRepository.save(order);
        buyer.getGames().addAll(products);
        userRepository.save(buyer);
//todo shoping list removeAll
        System.out.println("Successfully bought games:\n  -" + products.toString());

    }
}
