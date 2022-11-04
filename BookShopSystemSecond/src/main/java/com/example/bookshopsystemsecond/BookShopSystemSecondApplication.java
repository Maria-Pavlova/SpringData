package com.example.bookshopsystemsecond;

import com.example.bookshopsystemsecond.models.entities.Author;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookShopSystemSecondApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookShopSystemSecondApplication.class, args);
        Author.builder().build();
    }

}
