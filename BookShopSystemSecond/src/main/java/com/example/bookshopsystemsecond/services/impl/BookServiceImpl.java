package com.example.bookshopsystemsecond.services.impl;

import com.example.bookshopsystemsecond.models.entities.*;
import com.example.bookshopsystemsecond.repositories.BookRepository;
import com.example.bookshopsystemsecond.services.AuthorService;
import com.example.bookshopsystemsecond.services.BookService;
import com.example.bookshopsystemsecond.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks(List<Book> books){
        this.bookRepository.saveAll(books);
    }

    @Override
    public boolean isDataSeeded() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public void findAllBooksAfterYear(int year) {
        this.bookRepository.findAllByReleaseDateAfter(LocalDate.of(year, 12, 31))
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    @Override
    public void findFindAllBooksWithReleaseDateBeforeYear(int year) {
        this.bookRepository.findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .toList()
                .forEach(System.out::println);
    }

    @Override
    public void findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
        this.bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(), book.getReleaseDate(), book.getCopies()))
                .toList()
                .forEach(System.out::println);
    }
}
