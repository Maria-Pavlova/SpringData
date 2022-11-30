package hiberspring.service.impl;
import hiberspring.domain.dtos.ProductRootDto;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static hiberspring.common.Constants.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final BranchRepository branchRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, XmlParser xmlParser,
                              ValidationUtil validationUtil, ModelMapper modelMapper,
                              BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean productsAreImported() {
        return productRepository.count()>0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return Files.readString(Path.of(PATH_PRODUCTS));
    }

    @Override
    public String importProducts() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        ProductRootDto productRootDto = xmlParser.fromFile(PATH_PRODUCTS, ProductRootDto.class);
        productRootDto
                .getProducts()
              .stream()
                .filter(productDto -> {
                    boolean isValid = validationUtil.isValid(productDto);
                    sb.append(isValid ? String.format(SUCCESSFUL_IMPORT_MESSAGE,
                            "Product", productDto.getName())
                            : INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    return isValid;
                })
                .map(productDto -> {
                    Product product = modelMapper.map(productDto, Product.class);
                    product.setBranch(branchRepository.findByName(productDto.getBranch()).get());
                    return product;
                })
                .forEach(productRepository::saveAndFlush);

        return sb.toString();
    }
}
