package com.example.jsonproductshop.services.impl;

import com.example.jsonproductshop.models.dto.CategoryStatisticDto;
import com.example.jsonproductshop.models.dto.ProductsInRangeDto;
import com.example.jsonproductshop.models.entities.Product;
import com.example.jsonproductshop.repositories.ProductRepository;
import com.example.jsonproductshop.services.ProductService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;


import static com.example.jsonproductshop.constants.FilePaths.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, Gson gson) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedProducts(List<Product> products) {
        if (productRepository.count() == 0) {
            productRepository.saveAllAndFlush(products);
        }
    }

    @Override
    public void getProductsInRange(float from, float to) throws IOException {
        BigDecimal fromPrice = BigDecimal.valueOf(from);
        BigDecimal toPrice = BigDecimal.valueOf(to);
        List<ProductsInRangeDto> dtos = productRepository.findByPriceBetweenAndBuyerIsNullOrderByPriceAsc(fromPrice, toPrice)
                .stream()
                .map(product -> {
                    ProductsInRangeDto productsInRangeDto = modelMapper.map(product, ProductsInRangeDto.class);
                    productsInRangeDto.setSeller(String.format("%s %s",
                            product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));

                    return productsInRangeDto;
                }).toList();

        String jsonContent = gson.toJson(dtos);
        System.out.println(jsonContent);
        writeToFile(OUTPUT_PATH + PRODUCTS_IN_RANGE_FILE, jsonContent);
    }

    @Override
    public void getCategoriesByProducts() throws IOException {
        List<CategoryStatisticDto> statisticDtos =
                productRepository.findCategoryByProductsCount()
                        .stream()
                        .toList();
        String jsonContent = gson.toJson(statisticDtos);
        System.out.println(jsonContent);
        writeToFile(OUTPUT_PATH + CATEGORIES_BY_PRODUCTS_FILE, jsonContent);
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }
}
