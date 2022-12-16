package com.example.workshopnextleveltechnologiesmvc.repositories;

import com.example.workshopnextleveltechnologiesmvc.data.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String name);
}
