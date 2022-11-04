package com.example.bookshopsystemsecond.services;

import com.example.bookshopsystemsecond.models.entities.Book;
import java.io.IOException;
import java.util.List;


public interface BookService {

    void seedBooks(List<Book> books) throws IOException;

    boolean isDataSeeded();

    void findAllBooksAfterYear(int year);

    void findFindAllBooksWithReleaseDateBeforeYear(int year);

    void findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);


}
