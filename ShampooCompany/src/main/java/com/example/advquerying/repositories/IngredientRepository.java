package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByNameStartingWithOrderByPrice(String m);

    List<Ingredient> findByNameInOrderByPrice(List<String> names);

    int deleteByName(String name);

    @Modifying
    @Query("UPDATE Ingredient SET price = price * :multiplier")
    int increasePriceByPercent(@Param("multiplier") BigDecimal percent);

    @Modifying
    @Query("UPDATE Ingredient SET price = price * :percent " +
            "WHERE name IN :names ")
    int increasePriceByNamesIn(BigDecimal percent, Set<String> names);
}
