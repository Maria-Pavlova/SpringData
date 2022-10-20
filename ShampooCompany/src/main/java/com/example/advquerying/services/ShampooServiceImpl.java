package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {
    private ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findBySize(Size size) {
        return this.shampooRepository.findBySize(size);
    }

    @Override
    public List<Shampoo> selectBySizeOrLabelId(Size size, int labelId) {
        return this.shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(size, labelId);
    }

    @Override
    public List<Shampoo> findByPriceGreaterThenOrderByPriceDesc(BigDecimal valueOf) {
        return this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(valueOf);
    }

    @Override
    public int countPriceLowerThan(BigDecimal valueOf) {
        return this.shampooRepository.countByPriceLessThan(valueOf);
    }

    @Override
    public List<Shampoo> findByIngredientsIn(Set<String> names) {
        return shampooRepository.findDistinctByIngredientsNames(names);
    }

    @Override
    public List<Shampoo> findByIngredientsCountLessThan(int count) {
        return shampooRepository.findByIngredientsCountLessThan(count);
    }
}
