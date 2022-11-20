package com.example.jsoncardealer;

import com.example.jsoncardealer.models.entities.Car;
import com.example.jsoncardealer.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;

@Component
public class Runner implements CommandLineRunner {
    private final CarService carService;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CustomerService customerService;
    private final SeedService seedService;
    private final SaleService saleService;
    private final BufferedReader bufferedReader;

    @Autowired
    public Runner(CarService carService, SupplierService supplierService, PartService partService,
                  CustomerService customerService, SeedService seedService, SaleService saleService,
                  BufferedReader bufferedReader) {
        this.carService = carService;
        this.supplierService = supplierService;
        this.partService = partService;
        this.customerService = customerService;
        this.seedService = seedService;
        this.saleService = saleService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
     //   seedService.seedAllData();
     //   saleService.seedSales();

        System.out.println("Enter number of query:");
        int numOfQuery = Integer.parseInt(bufferedReader.readLine());
        switch (numOfQuery){
            case 1 -> customerService.getOrderedCustomers();
            case 2 -> carService.getCarsMakeFrom("Toyota");
            case 3 -> supplierService.getLocalSuppliers();
            case 4 -> carService.getCarsWithTheirParts();
            case 5 -> customerService.getCustomersTotalSales();
            case 6 -> saleService.getSalesSummary();
        }
    }
}
