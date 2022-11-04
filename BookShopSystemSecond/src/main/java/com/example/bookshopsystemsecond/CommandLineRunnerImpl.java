package com.example.bookshopsystemsecond;

import com.example.bookshopsystemsecond.services.AuthorService;
import com.example.bookshopsystemsecond.services.BookService;
import com.example.bookshopsystemsecond.services.CategoryService;
import com.example.bookshopsystemsecond.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final SeedService seedService;

    @Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, SeedService seedService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {

     //   seedService.seedAuthors();
      //  seedService.seedCategories();
      //  seedService.seedBooks();


        // 01.
//        bookService.findAllBooksAfterYear(2000);

        // 02.
//        bookService.findFindAllBooksWithReleaseDateBeforeYear(1990);

        // 03.
//        authorService.getAllAuthorsOrderByCountOfBooks();

        // 04.
//        bookService.findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate("George", "Powell");
    }

}
