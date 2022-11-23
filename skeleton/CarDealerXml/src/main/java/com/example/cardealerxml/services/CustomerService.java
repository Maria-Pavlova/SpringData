package com.example.cardealerxml.services;

import com.example.cardealerxml.models.entities.Customer;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CustomerService {

    void seedCustomers(List<Customer> customers);

    void getOrderedCustomers() throws IOException, JAXBException;

    void getCustomersTotalSales() throws IOException, JAXBException;
}
