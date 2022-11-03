package com.example.bookshopsystem.services;

import java.io.IOException;


public interface BookService {

    void seedBooks() throws IOException;

    void findAllBooksAfterYear(int year);

    void findFindAllBooksWithReleaseDateBeforeYear(int year);

    void findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);


}
