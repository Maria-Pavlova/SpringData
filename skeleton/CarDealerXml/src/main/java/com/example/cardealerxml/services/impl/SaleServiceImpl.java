package com.example.cardealerxml.services.impl;

import com.example.cardealerxml.models.dto.exportDto.sales.SalesExportDto;
import com.example.cardealerxml.models.dto.exportDto.sales.SalesExportRootDto;
import com.example.cardealerxml.models.entities.Car;
import com.example.cardealerxml.models.entities.Customer;
import com.example.cardealerxml.models.entities.Part;
import com.example.cardealerxml.models.entities.Sale;
import com.example.cardealerxml.repositories.CarRepository;
import com.example.cardealerxml.repositories.CustomerRepository;
import com.example.cardealerxml.repositories.SaleRepository;
import com.example.cardealerxml.services.SaleService;
import com.example.cardealerxml.utills.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.cardealerxml.constants.FilePath.*;


@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;


    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository,
                           CustomerRepository customerRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedSales() {
        Sale sale1 = new Sale();
        sale1.setCar(getRandomCar());
        sale1.setCustomer(getRandomCustomer());
        if (sale1.getCustomer().isYoungDriver()){
            sale1.setDiscountPercentage(5.0);
        }else {
            sale1.setDiscountPercentage(Double.valueOf(getRandomDiscount()));
        }


        Sale sale2 = new Sale();
        sale2.setCar(getRandomCar());
        sale2.setCustomer(getRandomCustomer());
        if (sale2.getCustomer().isYoungDriver()){
            sale2.setDiscountPercentage(5.0);
        }else {
            sale2.setDiscountPercentage(Double.valueOf(getRandomDiscount()));
        }

        Sale sale3 = new Sale();
        sale3.setCar(getRandomCar());
        sale3.setCustomer(getRandomCustomer());
        if (sale3.getCustomer().isYoungDriver()){
            sale3.setDiscountPercentage(5.0);
        }else {
            sale3.setDiscountPercentage(Double.valueOf(getRandomDiscount()));
        }

        Sale sale4 = new Sale();
        sale4.setCar(getRandomCar());
        sale4.setCustomer(getRandomCustomer());
        if (sale4.getCustomer().isYoungDriver()){
            sale4.setDiscountPercentage(5.0);
        }else {
            sale4.setDiscountPercentage(Double.valueOf(getRandomDiscount()));
        }

        List<Sale> sales = new ArrayList<>(List.of(sale1,sale2,sale3,sale4));
        if (saleRepository.count() == 0){
            saleRepository.saveAllAndFlush(sales);
        }

    }

    @Override
    public void getSalesSummary() throws JAXBException {
        List<SalesExportDto> salesExportDtos = saleRepository.findAll()
                .stream()
                .map(sale -> {
                    SalesExportDto dto = modelMapper.map(sale, SalesExportDto.class);
                    List<BigDecimal> prices = sale.getCar().getParts().stream().map(Part::getPrice).toList();
                    double price = prices.stream().mapToDouble(BigDecimal::doubleValue).sum();
                    dto.setPrice(BigDecimal.valueOf(price));
                    Double withDiscount = price - price * (sale.getDiscountPercentage() * 0.01);
                    dto.setPriceWithDiscount(BigDecimal.valueOf(withDiscount));
                    return dto;
                }).toList();

        SalesExportRootDto salesExportRootDto = new SalesExportRootDto(salesExportDtos);

        xmlParser.writeToFile(EXPORT_PATH + SALES_DISCOUNTS_FILE, salesExportRootDto);

    }


    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }

    private Integer getRandomDiscount() {
        List<Integer> discounts = new ArrayList<Integer>(List.of(0,5,10,15,20,30,40,50));
       Integer index = new Random().nextInt(discounts.size());
       return discounts.get(index);
    }

    private Customer getRandomCustomer() {
        long randomId = new Random().nextLong(1,customerRepository.count());
        return this.customerRepository.findById(randomId).get();
    }

    private Car getRandomCar() {
        long randomId = new Random().nextLong(1,carRepository.count());
        return this.carRepository.findById(randomId).get();
    }
}
