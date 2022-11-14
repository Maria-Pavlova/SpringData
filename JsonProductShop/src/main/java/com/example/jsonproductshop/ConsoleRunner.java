package com.example.jsonproductshop;

import com.example.jsonproductshop.services.CategoryService;
import com.example.jsonproductshop.services.ProductService;
import com.example.jsonproductshop.services.SeedService;
import com.example.jsonproductshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final SeedService seedService;
    private final BufferedReader bufferedReader;

    @Autowired
    public ConsoleRunner(UserService userService, CategoryService categoryService, ProductService productService, SeedService seedService, BufferedReader bufferedReader) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.seedService = seedService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {

//        seedService.seedCategories();
//       seedService.seedUsers();
//        seedService.seedProducts();
//
        System.out.println("Enter number of query:");

        int numOfQuery = Integer.parseInt(bufferedReader.readLine());
        switch (numOfQuery){
            case 1 -> productService.getProductsInRange(500,1000);
            case 2 -> userService.getUsersSoldProducts();
            case 3 -> productService.getCategoriesByProducts();
            case 4 -> userService.getUsersAndProducts();
        }



    }


}
