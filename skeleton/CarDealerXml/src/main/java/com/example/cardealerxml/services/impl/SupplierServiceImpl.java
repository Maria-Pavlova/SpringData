package com.example.cardealerxml.services.impl;

import com.example.cardealerxml.models.dto.exportDto.suppliers.LocalSuppliersDto;
import com.example.cardealerxml.models.dto.exportDto.suppliers.LocalSuppliersRootDto;
import com.example.cardealerxml.models.entities.Supplier;
import com.example.cardealerxml.repositories.SupplierRepository;
import com.example.cardealerxml.services.SupplierService;
import com.example.cardealerxml.utills.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Random;
import static com.example.cardealerxml.constants.FilePath.*;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;


    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedSuppliers(List<Supplier> suppliers) {
        if (supplierRepository.count() == 0) {
            supplierRepository.saveAllAndFlush(suppliers);
        }
    }

    @Override
    public Supplier gerRandomSupplier() {
        long randomId = new Random().nextLong(1L, supplierRepository.count());
        return supplierRepository.findById(randomId).orElse(null);
    }

    @Override
    public void getLocalSuppliers() throws JAXBException {
        List<LocalSuppliersDto> localSuppliersDtos = supplierRepository.findAllByImporterFalse()
                .stream()
                .map(supplier -> {
                    LocalSuppliersDto suppliersDto = modelMapper.map(supplier, LocalSuppliersDto.class);
                    suppliersDto.setPartsCount(supplier.getParts().size());
                    return suppliersDto;
                }).toList();

        LocalSuppliersRootDto localSuppliersRootDto = new LocalSuppliersRootDto(localSuppliersDtos);

        xmlParser.writeToFile(EXPORT_PATH + LOCAL_SUPPLIERS_FILE, localSuppliersRootDto);

    }

}
