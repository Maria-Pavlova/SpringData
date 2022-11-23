package com.example.cardealerxml.services.impl;

import com.example.cardealerxml.models.dto.exportDto.CustomersTotalSalesDto;
import com.example.cardealerxml.models.dto.exportDto.CustomersTotalSalesRootDto;
import com.example.cardealerxml.models.dto.exportDto.OrderedCustomersDto;
import com.example.cardealerxml.models.dto.exportDto.OrderedCustomersRootDto;
import com.example.cardealerxml.models.entities.Customer;
import com.example.cardealerxml.repositories.CustomerRepository;
import com.example.cardealerxml.services.CustomerService;
import com.example.cardealerxml.utills.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import static com.example.cardealerxml.constants.FilePath.*;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;

        this.xmlParser = xmlParser;
    }

    @Override
    public void seedCustomers(List<Customer> customers) {
        if (customerRepository.count() == 0) {
            customerRepository.saveAll(customers);
        }
    }

    @Override
    public void getOrderedCustomers() throws  JAXBException {
        List<OrderedCustomersDto> orderedCustomersDtos = customerRepository.findAllByOrderByBirthDateAscIsYoungDriverAsc()
                .stream()
                .map(customer -> modelMapper.map(customer, OrderedCustomersDto.class))
                .toList();
        OrderedCustomersRootDto orderedCustomersRootDto = new OrderedCustomersRootDto(orderedCustomersDtos);

        xmlParser.writeToFile(EXPORT_PATH + ORDERED_CUSTOMERS_FILE, orderedCustomersRootDto);

    }

    @Override
    public void getCustomersTotalSales() throws JAXBException {

        List<CustomersTotalSalesDto> customersTotalSalesDtos = customerRepository.findCustomersBySales()
                .stream()
                .toList();
        CustomersTotalSalesRootDto customersTotalSalesRootDto = new CustomersTotalSalesRootDto(customersTotalSalesDtos);
        xmlParser.writeToFile(EXPORT_PATH+CUSTOMERS_TOTAL_SALES_FILE, customersTotalSalesRootDto);

    }

}
