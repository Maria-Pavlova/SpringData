package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.models.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM authors as a ORDER BY a.books.size DESC ")
  //  @Query( "SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllByBooksSizeDesc();

}
