package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import com.example.springintro.service.model.BookInfo;
import com.example.springintro.service.model.Locate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;
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

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
        return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    // 01
    @Override
    public void findTitleByAgeRestriction(String ageRestriction) {
        AgeRestriction restriction = AgeRestriction.valueOf(ageRestriction.toUpperCase());
        this.bookRepository.findTitleByAgeRestriction(restriction)
                .forEach(System.out::println);
    }

    // 02.
    @Override
    public void findByEditionTypeAndCopiesLessThan(EditionType type, int copies) {
        bookRepository.findByEditionTypeAndCopiesLessThan(type, copies)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    // 03.
    @Override
    public void findByPriceLessThanOrGreaterThan(double lowerBound, double upperBound) {
        BigDecimal lessThan = BigDecimal.valueOf(lowerBound);
        BigDecimal moreThan = BigDecimal.valueOf(upperBound);
        this.bookRepository.findByPriceLessThanOrPriceGreaterThan(lessThan, moreThan)
                .forEach(book -> System.out.printf("%s - $%s%n",
                        book.getTitle(), book.getPrice()));
    }

    // 04.
    @Override
    public void findByReleaseDateIsNot(int year) {
        LocalDate before = LocalDate.of(year, 1, 1);
        LocalDate after = LocalDate.of(year, 12, 31);
        this.bookRepository.findByReleaseDateBeforeOrReleaseDateAfter(before, after)
                .forEach(book -> System.out.println(book.getTitle()));

    }

    // 05.
    @Override
    public void findByReleaseDateBefore(String inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(inputDate, formatter);
        this.bookRepository.findByReleaseDateBefore(date)
                .forEach(book -> System.out.printf("%s %s %.2f%n", book.getTitle(),
                        book.getEditionType(), book.getPrice()));
    }

    // 07.
    @Override
    public void findByTitleContains(String str) {
        this.bookRepository.findByTitleContains(str)
                .forEach(b -> System.out.println(b.getTitle()));

    }

    // 08.
    @Override
    public void findByAuthorWithLastNameStartsWith(String str1) {
        bookRepository.findByAuthor_LastNameStartsWith(str1)
                .forEach(b -> System.out.printf("%s (%s %s)%n", b.getTitle(),
                        b.getAuthor().getFirstName(), b.getAuthor().getLastName()));
    }

    // 09.
    @Override
    public void findByTitleLongerThan(int number) {
        int count = bookRepository.countByTitleIsGreaterThan(number);
        System.out.printf("There are %d books with longer title than %d symbols ",
                count, number);
    }


    //11.Reduced Book
    @Override
    public BookInfo findBookByTitle(String title) {
        Book book = this.bookRepository.findBookByTitle(title);
        BookInfo bookInfo = new BookInfo(book.getTitle(), book.getEditionType(), book.getAgeRestriction(), book.getPrice());
        return bookInfo;
    }

    //12.Increase Book Copies

    @Override
    public void printUpdatedCopiesCount(String date, int amount) {
        DateTimeFormatter formatter1 = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd MMM yyyy")
                .toFormatter(Locate.US);
        LocalDate localDate = LocalDate.parse(date, formatter1);

        int count = this.bookRepository.updateCopies(localDate, amount);
        System.out.printf("%d books are released after %s, so total of %d book copies were added",
                count, date, amount * count);

    }

    @Override
    public void deleteWithCopiesLessThan(int amount) {
        int deletedCount = this.bookRepository.deleteByCopiesLessThan(amount);
        System.out.println(deletedCount + " books were deleted.");
    }


    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
