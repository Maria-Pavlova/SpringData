package com.example.springintro.service;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.model.BookInfo;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);




    void findTitleByAgeRestriction(String ageRestriction);

    void findByEditionTypeAndCopiesLessThan(EditionType ty, int copies);

    void findByPriceLessThanOrGreaterThan(double lowerBound, double upperBound);

    void findByReleaseDateIsNot(int year);

    void findByReleaseDateBefore(String date);

    void findByTitleContains(String str);

    void findByAuthorWithLastNameStartsWith(String str1);

    void findByTitleLongerThan(int number);

    BookInfo findBookByTitle(String title);

    void printUpdatedCopiesCount(String date, int amount);

    void deleteWithCopiesLessThan(int amount);
}
