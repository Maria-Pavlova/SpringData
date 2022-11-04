package com.example.automappingobjects_lab.projection.repositories;

import com.example.automappingobjects_lab.projection.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
