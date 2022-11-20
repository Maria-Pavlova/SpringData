package com.example.jsoncardealer.repositories;

import com.example.jsoncardealer.models.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
