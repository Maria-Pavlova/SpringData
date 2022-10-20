package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


public interface ShampooService {

    List<Shampoo> findBySize(Size size);

    List<Shampoo> selectBySizeOrLabelId(Size size, int labelId);

    List<Shampoo> findByPriceGreaterThenOrderByPriceDesc(BigDecimal valueOf);

    int countPriceLowerThan(BigDecimal valueOf);

    List<Shampoo> findByIngredientsIn(Set<String> names);

    List<Shampoo> findByIngredientsCountLessThan(int count);


}
