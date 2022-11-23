package com.example.cardealerxml.repositories;

import com.example.cardealerxml.models.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
