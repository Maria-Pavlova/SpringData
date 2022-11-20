package com.example.jsoncardealer.services.impl;

import com.example.jsoncardealer.models.dtoExport.SupplierDto;
import com.example.jsoncardealer.models.entities.Supplier;
import com.example.jsoncardealer.repositories.SupplierRepository;
import com.example.jsoncardealer.services.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static com.example.jsoncardealer.constants.FilePath.*;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, Gson gson) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedSupplier(List<Supplier> suppliers) {
        if (supplierRepository.count() == 0) {
            supplierRepository.saveAll(suppliers);
        }
    }

    @Override
    public Supplier gerRandomSupplier() {
        long randomId = new Random().nextLong(1L, supplierRepository.count());
        return supplierRepository.findById(randomId).orElse(null);
    }

    @Override
    public void getLocalSuppliers() throws IOException {
        List<SupplierDto> supplierDtos = supplierRepository.findAllByImporterFalse()
                .stream()
                .map(supplier -> {
                    SupplierDto supplierDto = modelMapper.map(supplier, SupplierDto.class);
                    supplierDto.setPartsCount(supplier.getParts().size());
                    return supplierDto;
                }).toList();
        String json = gson.toJson(supplierDtos);
        System.out.println(json);
        writeToFile(EXPORT_PATH + LOCAL_SUPPLIERS_FILE, json);

    }
    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }
}
