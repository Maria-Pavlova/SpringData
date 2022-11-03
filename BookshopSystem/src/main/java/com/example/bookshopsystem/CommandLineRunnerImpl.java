package com.example.bookshopsystem;
import com.example.bookshopsystem.models.entities.Book;
import com.example.bookshopsystem.services.AuthorService;
import com.example.bookshopsystem.services.BookService;
import com.example.bookshopsystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {

//        seedData();

        // 01.
//        bookService.findAllBooksAfterYear(2000);

        // 02.
//        bookService.findFindAllBooksWithReleaseDateBeforeYear(1990);

        // 03.
//        authorService.getAllAuthorsOrderByCountOfBooks();

        // 04.
//        bookService.findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate("George", "Powell");
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
