package com.example.springintro.service;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findTitleByAgeRestriction(String ageRestriction);

    List<String> findByEditionTypeAndLessThanCopies(EditionType ty, int copies);

    List<Book> findByPriceLessThanOrGreaterThan(double lowerBound, double upperBound);
}
