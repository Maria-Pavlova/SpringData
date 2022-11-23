package com.example.cardealerxml.services.impl;

import com.example.cardealerxml.models.dto.rootDto.CarRootDto;
import com.example.cardealerxml.models.dto.rootDto.CustomerRootDto;
import com.example.cardealerxml.models.dto.rootDto.PartRootDto;
import com.example.cardealerxml.models.dto.rootDto.SupplierRootDto;
import com.example.cardealerxml.models.entities.Car;
import com.example.cardealerxml.models.entities.Customer;
import com.example.cardealerxml.models.entities.Part;
import com.example.cardealerxml.models.entities.Supplier;
import com.example.cardealerxml.services.*;

import com.example.cardealerxml.utills.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.example.cardealerxml.constants.FilePath.*;


@Service
public class SeedServiceImpl implements SeedService {
    private final CarService carService;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    public SeedServiceImpl(CarService carService, SupplierService supplierService,
                           PartService partService, CustomerService customerService,
                           ModelMapper modelMapper,XmlParser xmlParser) {
        this.carService = carService;
        this.supplierService = supplierService;
        this.partService = partService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;

    }

    @Override
    public void seedSuppliers() throws JAXBException, FileNotFoundException {

        SupplierRootDto supplierRootDto = xmlParser.fromFile(PATH_SUPPLIERS, SupplierRootDto.class);
        List<Supplier> suppliers = supplierRootDto.getSuppliers()
                .stream()
                .map(supplierDto -> modelMapper.map(supplierDto, Supplier.class))
                .toList();
        supplierService.seedSuppliers(suppliers);

   }

    @Override
    public void seedParts() throws IOException, JAXBException {

        List<Part> parts = xmlParser.fromFile(PATH_PARTS, PartRootDto.class)
                .getParts()
                .stream()
                .map(partImportDto -> {
                    Part part = modelMapper.map(partImportDto, Part.class);
                    part.setSupplier(supplierService.gerRandomSupplier());
                    return part;
                })
                .toList();
        partService.seedParts(parts);

    }

    @Override
    public void seedCars() throws IOException, JAXBException {
        List<Car> cars = xmlParser.fromFile(PATH_CARS, CarRootDto.class)
                .getCars()
                .stream()
                .map(carImportDto -> {
                    Car car = modelMapper.map(carImportDto, Car.class);
                    car.setParts(partService.getRandomParts());
                    return car;
                }).toList();
        carService.seedCars(cars);
    }

    @Override
    public void seedCustomers() throws IOException, JAXBException {
        List<Customer> customers = xmlParser.fromFile(PATH_CUSTOMERS, CustomerRootDto.class)
                .getCustomers()
                .stream()
                .map(customerImportDto -> modelMapper.map(customerImportDto, Customer.class))
                .toList();
        customerService.seedCustomers(customers);

    }

}
