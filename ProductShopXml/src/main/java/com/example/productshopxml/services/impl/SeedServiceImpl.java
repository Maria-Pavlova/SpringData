package com.example.productshopxml.services.impl;

import com.example.productshopxml.models.dto.importDto.*;
import com.example.productshopxml.models.entities.Category;
import com.example.productshopxml.models.entities.Product;
import com.example.productshopxml.models.entities.User;
import com.example.productshopxml.services.CategoryService;
import com.example.productshopxml.services.ProductService;
import com.example.productshopxml.services.SeedService;
import com.example.productshopxml.services.UserService;
import com.example.productshopxml.utills.ValidationUtil;
import com.example.productshopxml.utills.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import static com.example.productshopxml.constants.FilePaths.*;


@Service
public class SeedServiceImpl implements SeedService {
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public SeedServiceImpl(CategoryService categoryService, UserService userService, ProductService productService,
                           XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException, JAXBException {
        CategoryRootDto categoryRootDto = xmlParser.fromFile(PATH_CATEGORIES_XML, CategoryRootDto.class);
        List<Category> categories = categoryRootDto.getCategories()
                .stream()
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class)).toList();
        categoryService.seedCategories(categories);
    }

    @Override
    public void seedUsers() throws IOException, JAXBException {

        UsersRootXmlDto usersRootXmlDto = xmlParser.fromFile(PATH_USERS_XML, UsersRootXmlDto.class);
        List<User> users = usersRootXmlDto.getUsers()
                .stream()
                .filter(validationUtil::isValid)
                .map(userImportXmlDto -> modelMapper.map(userImportXmlDto, User.class))
                .toList();

        userService.seedUsers(users);
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {

        ProductsRootDto productsRootDto = xmlParser.fromFile(PATH_PRODUCTS_XML, ProductsRootDto.class);
        List<Product> products = productsRootDto.getProducts()
                .stream()
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(userService.getRandomUser());
                    if (product.getPrice().compareTo(BigDecimal.valueOf(400L)) < 0) {
                        product.setBuyer(userService.getRandomUser());
                    }
                    product.setCategories(categoryService.getRandomCategories());
                    return product;
                })
                .toList();

        productService.seedProducts(products);
    }
}
