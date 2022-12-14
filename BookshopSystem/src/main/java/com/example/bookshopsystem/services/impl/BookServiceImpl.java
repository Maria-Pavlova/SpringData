package com.example.bookshopsystem.services.impl;

import com.example.bookshopsystem.models.entities.*;
import com.example.bookshopsystem.repositories.BookRepository;
import com.example.bookshopsystem.services.AuthorService;
import com.example.bookshopsystem.services.BookService;
import com.example.bookshopsystem.services.CategoryService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;


    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {

        if (bookRepository.count() > 0) {
            return;
        }
        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(line -> {
                    String[] bookInfo = line.split("\\s+");

                    Book book = createBook(bookInfo);
                    this.bookRepository.save(book);
                });
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


    private Book createBook(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate.parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));
        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(editionType, releaseDate, copies, price,
                ageRestriction, title, author, categories);
    }
}
