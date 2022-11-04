package com.example.bookshopsystemsecond.services;

import com.example.bookshopsystemsecond.models.entities.Category;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories(List<Category> categories) throws IOException;

    boolean isDataSeeded();

    Set<Category> getRandomCategories();
}
