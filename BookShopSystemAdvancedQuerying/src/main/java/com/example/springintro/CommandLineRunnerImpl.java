package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import com.example.springintro.service.model.BookInfo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // 1. Book Titles by Age Restriction
//        this.bookService.findTitleByAgeRestriction(scanner.nextLine());

        // 2. Golden Books
//        this.bookService.findByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000);

        // 3. Books By Price
//        this.bookService.findByPriceLessThanOrGreaterThan(5, 40);


        // 4. Not Released Books
//        this.bookService.findByReleaseDateIsNot(Integer.parseInt(scanner.nextLine()));


        // 5.Books Released Before Date
//        String date = scanner.nextLine();
//        this.bookService.findByReleaseDateBefore(date);


        // 6.Authors Search
//        String string = scanner.nextLine();
//        this.authorService.findByFirstNameEndsWith(string);


        // 7. Book Search
//        String str = scanner.nextLine();
//        this.bookService.findByTitleContains(str)


        // 8. Book Titles Search
//        String str1 = scanner.nextLine();
//        this.bookService.findByAuthorWithLastNameStartsWith(str1)
//

        // 9. Count Books
//        int number = Integer.parseInt(scanner.nextLine());
//        this.bookService.findByTitleLongerThan(number);


        //   10. Total Book Copies
//        this.authorService.countCopiesByAuthor();


        // 11. Reduced Book (with class BookInfo)
//        String title = scanner.nextLine();
//        BookInfo info = this.bookService.findBookByTitle(title);
//        System.out.printf("%s %s %s %.2f", info.getTitle(), info.getEditionType(),
//                                        info.getAgeRestriction(), info.getPrice());


        // 12. Increase Book Copies
//        String date = scanner.nextLine();
//        int amount = Integer.parseInt(scanner.nextLine());
//        this.bookService.printUpdatedCopiesCount(date, amount);


        // 13. Remove Books
//        int amount = Integer.parseInt(scanner.nextLine());
//        this.bookService.deleteWithCopiesLessThan(amount);


        //      seedData();
        // printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        //   printAllAuthorsAndNumberOfTheirBooks();
        //     printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
