package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findAllByNameStartingWithOrderByPrice(String m) {
        return ingredientRepository.findAllByNameStartingWithOrderByPrice(m);
    }

    @Override
    public List<Ingredient> findByNameInNames(List<String> names) {
        return ingredientRepository.findByNameInOrderByPrice(names);
    }

    @Override
    @Transactional
    public int deleteIngredientByGivenName(String name) {
        return ingredientRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public int increasePriceByPercent(double percent) {
        BigDecimal actualPercent = BigDecimal.valueOf(percent);
        return ingredientRepository.increasePriceByPercent(actualPercent);
    }

    @Override
    @Transactional
    public int increasePriceByNamesIn(BigDecimal percent, Set<String> names) {
        return ingredientRepository.increasePriceByNamesIn(percent, names);
    }


}
