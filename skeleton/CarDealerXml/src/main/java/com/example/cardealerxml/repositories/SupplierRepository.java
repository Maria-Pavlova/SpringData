package com.example.cardealerxml.repositories;

import com.example.cardealerxml.models.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT s FROM Supplier s WHERE s.isImporter IS FALSE")
    List<Supplier> findAllByImporterFalse();

}
//new com.example.jsoncardealer.models.dtoExport.SupplierDto (s.id, s.name, count(s.parts))