package com.example.jsonproductshop.services.impl;

import com.example.jsonproductshop.models.entities.Category;
import com.example.jsonproductshop.repositories.CategoryRepository;
import com.example.jsonproductshop.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

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
            categoryRepository.saveAllAndFlush(categories);
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

//        IntStream.range(1,returnedCount).forEach(number-> {
//            Category category = this.categoryRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
//            categories.add(category);
//        });
}
