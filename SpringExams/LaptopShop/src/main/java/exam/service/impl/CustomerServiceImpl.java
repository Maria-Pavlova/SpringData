package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.CustomerDto;
import exam.model.entity.Customer;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static exam.constants.filePath.PATH_CUSTOMERS;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, TownRepository townRepository) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }


    @Override
    public boolean areImported() {
        return customerRepository.count()>0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(PATH_CUSTOMERS));
    }

    @Override
    public String importCustomers() throws IOException {
        StringBuilder sb = new StringBuilder();
        CustomerDto[] customerDtos = gson.fromJson(this.readCustomersFileContent(), CustomerDto[].class);
        Arrays.stream(customerDtos)
                .filter(customerDto -> {
                    boolean isValid = validationUtil.isValid(customerDto);
                    if (customerRepository.findByEmail(customerDto.getEmail()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported Customer %s %s - %s",
                            customerDto.getFirstName(),customerDto.getLastName(),customerDto.getEmail())
                            : "Invalid Customer").append(System.lineSeparator());

                    return isValid;
                })
                .map(customerDto -> {
                    Customer customer = modelMapper.map(customerDto, Customer.class);
                    customer.setTown(townRepository.findByName(customer.getTown().getName()).get());
                    return customer;
                })
                .forEach(customerRepository::saveAndFlush);


        return sb.toString();
    }
}
