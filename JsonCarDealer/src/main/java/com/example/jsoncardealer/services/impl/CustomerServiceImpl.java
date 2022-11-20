package com.example.jsoncardealer.services.impl;

import com.example.jsoncardealer.models.dtoExport.CustomerExportDto;
import com.example.jsoncardealer.models.dtoExport.CustomersTotalSalesDto;
import com.example.jsoncardealer.models.entities.Car;
import com.example.jsoncardealer.models.entities.Customer;
import com.example.jsoncardealer.models.entities.Part;
import com.example.jsoncardealer.models.entities.Sale;
import com.example.jsoncardealer.repositories.CustomerRepository;
import com.example.jsoncardealer.services.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static com.example.jsoncardealer.constants.FilePath.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, Gson gson) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedCustomers(List<Customer> customers) {
        if (customerRepository.count() == 0) {
            customerRepository.saveAll(customers);
        }
    }


    @Override
    public void getOrderedCustomers() throws IOException {
        List<CustomerExportDto> customerExportDtos = customerRepository.findAllByOrderByBirthDateAscIsYoungDriverAsc()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerExportDto.class))
                .toList();
        String json = gson.toJson(customerExportDtos);

        System.out.println(json);
        writeToFile(EXPORT_PATH + ORDERED_CUSTOMERS_FILE, json);
    }

    @Override
    public void getCustomersTotalSales() throws IOException {

        List<Customer> customers = customerRepository.findCustomersBySales();

        List<CustomersTotalSalesDto> totalSalesDtos =
                customers.stream()
                        .map(customer -> {
                            CustomersTotalSalesDto dto = modelMapper.map(customer, CustomersTotalSalesDto.class);
                            dto.setBoughtCars(customer.getSales().size());

                            List<Double> discounts = customer.getSales().stream().map(Sale::getDiscountPercentage).toList();
                            List<Car> cars = customer.getSales().stream().map(Sale::getCar).toList();
                            double totalPrice = 0;
                            double spentMoney = 0;
                            for (Car car : cars) {
                                double priceOfCar = car.getParts().stream().map(Part::getPrice)
                                        .mapToDouble(BigDecimal::doubleValue).sum();
                                totalPrice += priceOfCar;
                                spentMoney = totalPrice - (totalPrice * discounts.get(0) * 0.01);
                            }
                            dto.setSpentMoney(BigDecimal.valueOf(spentMoney));
                            return dto;
                        })
                        .sorted(Comparator.comparing(CustomersTotalSalesDto::getSpentMoney).reversed())
                        .collect(Collectors.toList());

        String json = gson.toJson(totalSalesDtos);
        System.out.println(json);
        writeToFile(EXPORT_PATH + CUSTOMERS_TOTAL_SALES_FILE, json);
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }
}
