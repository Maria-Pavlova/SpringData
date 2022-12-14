package com.example.productshopxml.repositories;
import com.example.productshopxml.models.dto.categories.CategoryInfoDto;
import com.example.productshopxml.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal fromPrice, BigDecimal toPrice);

    @Query("SELECT new com.example.productshopxml.models.dto.categories.CategoryInfoDto(c.name, count(p), avg(p.price), sum(p.price))" +
            " FROM Product p JOIN p.categories c GROUP BY c.name ORDER BY count(p) DESC")
    List<CategoryInfoDto> getCategoriesByProducts();


}
