package com.example.jsoncardealer.services.impl;

import com.example.jsoncardealer.models.dtoImport.CarImportDto;
import com.example.jsoncardealer.models.dtoImport.CustomerDto;
import com.example.jsoncardealer.models.dtoImport.PartDto;
import com.example.jsoncardealer.models.dtoImport.SupplierDto;
import com.example.jsoncardealer.models.entities.Car;
import com.example.jsoncardealer.models.entities.Customer;
import com.example.jsoncardealer.models.entities.Part;
import com.example.jsoncardealer.models.entities.Supplier;
import com.example.jsoncardealer.services.*;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static com.example.jsoncardealer.constants.FilePath.*;

@Service
public class SeedServiceImpl implements SeedService {
    private final CarService carService;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public SeedServiceImpl(CarService carService, SupplierService supplierService,
                           PartService partService, CustomerService customerService,
                           ModelMapper modelMapper, Gson gson) {
        this.carService = carService;
        this.supplierService = supplierService;
        this.partService = partService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public void seedSuppliers() throws IOException {
        SupplierDto[] supplierDtos = gson.fromJson(Files.readString(Path.of(PATH_SUPPLIERS)), SupplierDto[].class);
        List<Supplier> suppliers = Arrays.stream(supplierDtos)
                .map(SupplierDto -> modelMapper.map(SupplierDto, Supplier.class)).toList();
        supplierService.seedSupplier(suppliers);

    }

    @Override
    public void seedParts() throws IOException {
        PartDto[] partDtos = gson.fromJson(Files.readString(Path.of(PATH_PARTS)), PartDto[].class);
        List<Part> parts = Arrays.stream(partDtos)
                .map(partDto -> {
                    Part part = modelMapper.map(partDto, Part.class);
                    part.setSupplier(supplierService.gerRandomSupplier());
                    return part;
                }).toList();
        partService.seedParts(parts);
    }

    @Override
    public void seedCars() throws IOException {
        CarImportDto[] carImportDtos = gson.fromJson(Files.readString(Path.of(PATH_CARS)), CarImportDto[].class);
        List<Car> cars = Arrays.stream(carImportDtos)
                .map(carImportDto -> {
                    Car car = modelMapper.map(carImportDto, Car.class);
                    car.setParts(partService.getRandomParts());
                    return car;
                }).toList();
        carService.seedCars(cars);
    }

    @Override
    public void seedCustomers() throws IOException {

        String fileContent = Files.readString(Path.of(PATH_CUSTOMERS));
        CustomerDto[] customerDtos = gson.fromJson(fileContent, CustomerDto[].class);

        List<Customer> customers = Arrays.stream(customerDtos)
                .map(customerDto -> modelMapper.map(customerDto, Customer.class))
                .toList();

        customerService.seedCustomers(customers);
    }

    @Override
    public void seedSales() {

    }
}
