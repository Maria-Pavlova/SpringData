package com.example.bookshopsystem.services.impl;

import com.example.bookshopsystem.models.entities.Category;
import com.example.bookshopsystem.repositories.CategoryRepository;
import com.example.bookshopsystem.services.CategoryService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORIES_FILE_PATH = "src/main/resources/files/categories.txt";

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() > 0){
            return;
        }

        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(line -> !line.isEmpty())
                .forEach(name -> {
                    Category category = new Category(name);

                    categoryRepository.save(category);
                });
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int randInt = ThreadLocalRandom
                .current().nextInt(1,3);
        long count = categoryRepository.count();

        for (int i = 0; i < randInt; i++) {
            long randId = ThreadLocalRandom
                    .current().nextLong(1, count + 1);

            Category category = categoryRepository
                    .findById(randId).orElse(null);

            categories.add(category);
        }
        return categories;
    }
}
