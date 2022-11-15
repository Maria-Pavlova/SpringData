package com.example.jsonproductshop.repositories;

import com.example.jsonproductshop.models.dto.CategoryStatisticDto;
import com.example.jsonproductshop.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal fromPrice, BigDecimal toPrice);

    @Query("SELECT new com.example.jsonproductshop.models.dto.CategoryStatisticDto( c.name, count(p), avg(p.price), sum(p.price)) " +
            "FROM Product p JOIN p.categories c GROUP BY c ORDER BY count (p) DESC")
    List<CategoryStatisticDto> findCategoryByProductsCount();

}
