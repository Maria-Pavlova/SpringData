package com.example.bookshopsystemsecond.services.impl;

import com.example.bookshopsystemsecond.constants.FilePath;
import com.example.bookshopsystemsecond.models.entities.Author;
import com.example.bookshopsystemsecond.models.entities.Book;
import com.example.bookshopsystemsecond.models.entities.Category;
import com.example.bookshopsystemsecond.models.enums.AgeRestriction;
import com.example.bookshopsystemsecond.models.enums.EditionType;
import com.example.bookshopsystemsecond.services.AuthorService;
import com.example.bookshopsystemsecond.services.BookService;
import com.example.bookshopsystemsecond.services.CategoryService;
import com.example.bookshopsystemsecond.services.SeedService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SeedServiceImpl implements SeedService {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (!this.authorService.isDataSeeded()) {
            this.authorService
                    .seedAuthors(Files.readAllLines(Path.of(FilePath.AUTHORS_FILE_PATH))
                            .stream()
                            .filter(s -> !s.isBlank())
                            .map(fullName -> Author.builder()
                                    .firstName(fullName.split("\\s+")[0])
                                    .lastName(fullName.split("\\s+")[1])
                                    .build())
                            .collect(Collectors.toList()));
        }
    }

    @Override
    public void seedBooks() throws IOException {
        if (!bookService.isDataSeeded()) {
        List<Book> books = Files.readAllLines(Path.of(FilePath.BOOKS_FILE_PATH))
                .stream()
                .filter(line -> !line.isBlank())
                .map(line -> {
                    String[] bookInfo = line.split("\\s+");
                    String title = Arrays.stream(bookInfo)
                            .skip(5).collect(Collectors.joining(" "));

                    return Book.builder()
                            .editionType(EditionType.values()[Integer.parseInt(bookInfo[0])])
                            .releaseDate(LocalDate.parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy")))
                            .copies(Integer.parseInt(bookInfo[2]))
                            .price(new BigDecimal(bookInfo[3]))
                            .ageRestriction(AgeRestriction.values()[Integer.parseInt(bookInfo[4])])
                            .author(authorService.getRandomAuthor())
                            .categories(categoryService.getRandomCategories())
                            .title(title)
                            .build();
                }).toList();

        this.bookService.seedBooks(books);
        }
    }


    @Override
    public void seedCategories() throws IOException {
        if (!categoryService.isDataSeeded()) {
            this.categoryService
                    .seedCategories(Files.readAllLines(Path.of(FilePath.CATEGORIES_FILE_PATH))
                            .stream()
                            .filter(line -> !line.isBlank())
                            .map(name -> Category.builder().name(name)
                                    .build())
                            .collect(Collectors.toList()));
        }
    }
}
