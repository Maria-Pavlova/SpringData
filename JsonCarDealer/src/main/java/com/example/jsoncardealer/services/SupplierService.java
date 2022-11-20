package com.example.jsoncardealer.services;

import com.example.jsoncardealer.models.entities.Supplier;

import java.io.IOException;
import java.util.List;

public interface SupplierService {

    void seedSupplier(List<Supplier> suppliers);

    Supplier gerRandomSupplier();

    void getLocalSuppliers() throws IOException;
}
