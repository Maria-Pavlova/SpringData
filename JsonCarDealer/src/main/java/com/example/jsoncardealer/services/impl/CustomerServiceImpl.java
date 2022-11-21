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

        List<CustomersTotalSalesDto> customersBySales = customerRepository.findCustomersBySales();
        String json = gson.toJson(customersBySales);
        System.out.println(json);
        writeToFile(EXPORT_PATH + CUSTOMERS_TOTAL_SALES_FILE, json);
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }
}
