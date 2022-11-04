package com.example.bookshopsystemsecond.services.impl;

import com.example.bookshopsystemsecond.models.entities.Category;
import com.example.bookshopsystemsecond.repositories.CategoryRepository;
import com.example.bookshopsystemsecond.services.CategoryService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories(List<Category> categories) throws IOException {
        categoryRepository.saveAll(categories);
    }

    @Override
    public boolean isDataSeeded() {
        return categoryRepository.count() > 0;
    }


    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int randInt = new Random().nextInt(1,3);

        long count = categoryRepository.count();

        for (int i = 0; i < randInt; i++) {
            long randId = new Random().nextLong(1, count + 1);
            Category category = categoryRepository.findById(randId).orElse(null);
            categories.add(category);
        }
        return categories;
    }
}
