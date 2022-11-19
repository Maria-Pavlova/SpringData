package com.example.productshopxml.services.impl;

import com.example.productshopxml.models.dto.products.ProductInfoDto;
import com.example.productshopxml.models.dto.categories.CategoriesRootDto;
import com.example.productshopxml.models.dto.categories.CategoryInfoDto;
import com.example.productshopxml.models.dto.products.ProductsRootDto;
import com.example.productshopxml.models.entities.Product;
import com.example.productshopxml.repositories.ProductRepository;
import com.example.productshopxml.services.ProductService;
import com.example.productshopxml.utills.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.List;
import static com.example.productshopxml.constants.FilePaths.*;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;


    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;

    }

    @Override
    public void seedProducts(List<Product> products) {
        if (productRepository.count() == 0) {
            productRepository.saveAllAndFlush(products);
        }
    }

    @Override
    public void getProductsInRange(float from, float to) throws JAXBException {
        BigDecimal fromPrice = BigDecimal.valueOf(from);
        BigDecimal toPrice = BigDecimal.valueOf(to);

        List<ProductInfoDto> productInfoDtos = productRepository.findByPriceBetweenAndBuyerIsNullOrderByPriceAsc(fromPrice, toPrice)
                .stream()
                .map(product -> {
                    ProductInfoDto productInfoDto = modelMapper.map(product, ProductInfoDto.class);
                    productInfoDto.setSeller((product.getSeller().getFirstName() == null
                            ? "" : product.getSeller().getFirstName())
                            + " " + product.getSeller().getLastName());
                    return productInfoDto;
                }).toList();

        ProductsRootDto productsRootDto = new ProductsRootDto(productInfoDtos);
        xmlParser.writeToFile(OUTPUT_PATH_XML + PRODUCTS_IN_RANGE_FILE_XML, productsRootDto);

    }

    @Override
    public void getCategoriesByProducts() throws JAXBException {

        List<CategoryInfoDto> categoryInfoDtos = productRepository.getCategoriesByProducts()
                .stream().toList();
        CategoriesRootDto categoriesRootDto = new CategoriesRootDto(categoryInfoDtos);

        xmlParser.writeToFile(OUTPUT_PATH_XML + CATEGORIES_BY_PRODUCTS_FILE_XML, categoriesRootDto);

    }


}
