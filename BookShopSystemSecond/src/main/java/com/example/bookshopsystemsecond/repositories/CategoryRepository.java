package com.example.bookshopsystemsecond.repositories;


import com.example.bookshopsystemsecond.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
