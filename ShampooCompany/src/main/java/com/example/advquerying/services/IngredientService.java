package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface IngredientService {
    List<Ingredient> findAllByNameStartingWithOrderByPrice(String m);

    List<Ingredient> findByNameInNames(List<String> names);

    int deleteIngredientByGivenName(String name);

    int increasePriceByPercent(double percent);

    int increasePriceByNamesIn(BigDecimal percent, Set<String> herbs);
}

