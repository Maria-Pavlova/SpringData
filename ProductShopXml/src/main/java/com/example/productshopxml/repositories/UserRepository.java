package com.example.productshopxml.repositories;

import com.example.productshopxml.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByProductsSoldBuyerIsNotNullOrderByProductsSoldBuyerFirstName();

//    @Query("SELECT u FROM User u " +
//           "JOIN u.productsSold p WHERE p.buyer IS NOT NULL ORDER BY u.lastName,u.firstName")
//    List<User> findAllWithSoldProducts();


    @Query("SELECT u FROM User u " +
            "JOIN u.productsSold p WHERE p.buyer IS NOT NULL " +
            "ORDER BY size(u.productsSold) DESC , u.lastName")
    List<User> findAllUsersAndSoldProducts();
}
