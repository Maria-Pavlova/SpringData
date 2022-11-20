package com.example.jsoncardealer.services;

import com.example.jsoncardealer.models.entities.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerService {

    void seedCustomers(List<Customer> customers);

    void getOrderedCustomers() throws IOException;

    void getCustomersTotalSales() throws IOException;
}
