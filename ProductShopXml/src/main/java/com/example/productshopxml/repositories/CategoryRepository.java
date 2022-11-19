package com.example.productshopxml.repositories;

import com.example.productshopxml.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @Query(value = "select * from product_shop.users order by rand() LIMIT 1", nativeQuery = true)
//    Optional<Category> getRandomEntity();



}
