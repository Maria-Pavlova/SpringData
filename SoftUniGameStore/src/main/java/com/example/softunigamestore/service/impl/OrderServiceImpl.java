package com.example.softunigamestore.service.impl;

import com.example.softunigamestore.exceptions.ExistingGameException;
import com.example.softunigamestore.models.entity.ShoppingCart;
import com.example.softunigamestore.models.entity.Game;
import com.example.softunigamestore.models.entity.Order;
import com.example.softunigamestore.models.entity.User;
import com.example.softunigamestore.repositories.GameRepository;
import com.example.softunigamestore.repositories.OrderRepository;
import com.example.softunigamestore.repositories.ShoppingCartRepository;
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
    private ShoppingCart currentShoppingCart;
    private UserRepository userRepository;
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, GameRepository gameRepository,
                            UserService userService, UserRepository userRepository,
                            ShoppingCartRepository shoppingCartRepository) {
        this.orderRepository = orderRepository;
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;

    }


    @Transactional
    @Override
    public void addItem(String gameName) {
        Game game = gameRepository.findByTitle(gameName);
            currentShoppingCart = new ShoppingCart();
        if (userService.getOwnedGames().contains(game)) {
            throw new ExistingGameException("The Game already exists!");
        } else if (currentShoppingCart.getShoppingList().contains(game)) {
            throw new ExistingGameException("The Game already exists!");
        } else {
            currentShoppingCart.addProduct(game);
            shoppingCartRepository.saveAndFlush(currentShoppingCart);
            System.out.println(gameName + " added to cart.");
        }
    }

    @Transactional
    @Override
    public void removeItem(String gameName) {
        Game gameToDelete = gameRepository.findByTitle(gameName);

        if (currentShoppingCart.getShoppingList().contains(gameToDelete)) {
            currentShoppingCart.removeGame(gameToDelete);
            shoppingCartRepository.saveAndFlush(currentShoppingCart);
            System.out.println(gameToDelete.getTitle() + " removed from cart.");

        }else {
            System.out.println("The game is not in the shopping list.");
        }

    }

    @Transactional
    @Override
    public void buyItem() {

        Set<Game> shoppingList = currentShoppingCart.getShoppingList();
        User buyer = userService.getLoggedInUser();
          Order order = new Order(buyer,currentShoppingCart);
        Set<Game> products = order.getProducts();
        products.addAll(shoppingList);
        orderRepository.save(order);
        buyer.getGames().addAll(products);
        userRepository.save(buyer);

        System.out.println("Successfully bought games:");
        shoppingList.stream().map(Game::getTitle)
                .forEach(System.out::println);
        shoppingList.clear();

    }
}
