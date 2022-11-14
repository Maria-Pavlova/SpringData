package com.example.jsonproductshop.services.impl;

import com.example.jsonproductshop.constants.FilePaths;
import com.example.jsonproductshop.models.dto.importDto.CategorySeedDto;
import com.example.jsonproductshop.models.dto.importDto.ProductSeedDto;
import com.example.jsonproductshop.models.dto.importDto.UserSeedDto;
import com.example.jsonproductshop.models.entities.Category;
import com.example.jsonproductshop.models.entities.Product;
import com.example.jsonproductshop.models.entities.User;
import com.example.jsonproductshop.services.CategoryService;
import com.example.jsonproductshop.services.ProductService;
import com.example.jsonproductshop.services.SeedService;
import com.example.jsonproductshop.services.UserService;
import com.example.jsonproductshop.utills.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static com.example.jsonproductshop.constants.FilePaths.PATH_PRODUCTS;
import static com.example.jsonproductshop.constants.FilePaths.PATH_USERS;

@Service
public class SeedServiceImpl implements SeedService {
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public SeedServiceImpl(CategoryService categoryService, UserService userService, ProductService productService, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {
        String fileContent = Files.readString(Path.of(FilePaths.PATH_CATEGORIES));
        CategorySeedDto[] categorySeedDtos = gson.fromJson(fileContent, CategorySeedDto[].class);
        List<Category> categories = Arrays.stream(categorySeedDtos)
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class)).toList();
        categoryService.seedCategories(categories);
    }

    @Override
    public void seedUsers() throws IOException {
        UserSeedDto[] userSeedDtos = gson.fromJson(Files.readString(Path.of(PATH_USERS)), UserSeedDto[].class);
        List<User> users = Arrays.stream(userSeedDtos)
                .filter(validationUtil::isValid)
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class)).toList();
        userService.seedUsers(users);

    }

    @Override
    public void seedProducts() throws IOException {

        ProductSeedDto[] productSeedDtos = gson.fromJson(Files.readString(Path.of(PATH_PRODUCTS)), ProductSeedDto[].class);

        List<Product> products = Arrays.stream(productSeedDtos)
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(userService.getRandomUser());
                    if (product.getPrice().compareTo(BigDecimal.valueOf(350L)) < 0) {
                        product.setBuyer(userService.getRandomUser());
                    }
                    product.setCategories(categoryService.getRandomCategories());
                    return product;
                }).toList();

        productService.seedProducts(products);
    }
}
