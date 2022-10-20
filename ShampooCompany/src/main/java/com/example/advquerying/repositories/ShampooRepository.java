package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findBySize(Size size);

    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, long labelId);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal valueOf);

    int countByPriceLessThan(BigDecimal valueOf);

    @Query("SELECT DISTINCT (s) FROM Shampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE i.name IN :names")
    List<Shampoo> findDistinctByIngredientsNames(Set<String> names);

    @Query("SELECT s FROM Shampoo s WHERE s.ingredients.size < :count")
    List<Shampoo> findByIngredientsCountLessThan(int count);
}

