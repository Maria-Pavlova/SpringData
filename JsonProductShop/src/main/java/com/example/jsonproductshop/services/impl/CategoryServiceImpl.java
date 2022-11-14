package com.example.jsonproductshop.services.impl;

import com.example.jsonproductshop.models.entities.Category;
import com.example.jsonproductshop.repositories.CategoryRepository;
import com.example.jsonproductshop.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {

    private  final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories(List<Category> categories){
        if (categoryRepository.count() == 0) {
            categoryRepository.saveAll(categories);
        }
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int returnedCount = new Random().nextInt(1,3);

        for (int i = 0; i < returnedCount; i++) {
            long randId = new Random().nextLong(1, categoryRepository.count() + 1);

            categories.add(categoryRepository.findById(randId).orElse(null));
        }
        return categories;
    }


}
