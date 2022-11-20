package com.example.jsoncardealer.services.impl;

import com.example.jsoncardealer.models.dtoExport.CustomersTotalSalesDto;
import com.example.jsoncardealer.models.dtoExport.SalesDiscountsDto;
import com.example.jsoncardealer.models.entities.Car;
import com.example.jsoncardealer.models.entities.Customer;
import com.example.jsoncardealer.models.entities.Part;
import com.example.jsoncardealer.models.entities.Sale;
import com.example.jsoncardealer.repositories.CarRepository;
import com.example.jsoncardealer.repositories.CustomerRepository;
import com.example.jsoncardealer.repositories.SaleRepository;
import com.example.jsoncardealer.services.SaleService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import static com.example.jsoncardealer.constants.FilePath.*;


@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, ModelMapper modelMapper, Gson gson) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
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
    public void getSalesSummary() throws IOException {
        List<Sale> sales = saleRepository.findAll();
        List<SalesDiscountsDto> salesDiscountsDtos = sales.stream()
                .map(sale -> {
                    SalesDiscountsDto dto = modelMapper.map(sale, SalesDiscountsDto.class);
                    List<BigDecimal> prices = sale.getCar().getParts().stream().map(Part::getPrice).toList();
                    double price = prices.stream().mapToDouble(BigDecimal::doubleValue).sum();
                    dto.setPrice(BigDecimal.valueOf(price));
                    Double withDiscount = price - price * (sale.getDiscountPercentage() * 0.01);
                    dto.setPriceWithDiscount(BigDecimal.valueOf(withDiscount));
                    return dto;
                }).toList();
        String json = gson.toJson(salesDiscountsDtos);
        System.out.println(json);
        writeToFile(EXPORT_PATH + SALES_DISCOUNTS_FILE, json);

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
