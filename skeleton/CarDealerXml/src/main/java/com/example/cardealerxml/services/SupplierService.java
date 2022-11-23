package com.example.cardealerxml.services;

import com.example.cardealerxml.models.entities.Supplier;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface SupplierService {

    void seedSuppliers(List<Supplier> suppliers);

    Supplier gerRandomSupplier();

    void getLocalSuppliers() throws IOException, JAXBException;
}
