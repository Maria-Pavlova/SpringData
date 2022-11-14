package com.example.jsonproductshop.services;

import com.example.jsonproductshop.models.entities.Category;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {

    void seedCategories(List<Category> categories) throws IOException;

    Set<Category> getRandomCategories();


}
