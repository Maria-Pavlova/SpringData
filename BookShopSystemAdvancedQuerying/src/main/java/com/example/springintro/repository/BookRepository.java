package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    //01.
    @Query("SELECT b.title FROM Book b WHERE b.ageRestriction = :restriction")
    List<String> findTitleByAgeRestriction(AgeRestriction restriction);

    //02.
    List<Book> findByEditionTypeAndCopiesLessThan(EditionType type, int copies);

    //03.
    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lessThan, BigDecimal moreThan);

    // 04.
    List<Book> findByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    // 05.
    List<Book> findByReleaseDateBefore(LocalDate date);

    // 07.
    List<Book> findByTitleContains(String str);

    // 08.
    List<Book> findByAuthor_LastNameStartsWith(String str1);

    // 09.
    @Query("SELECT COUNT(b.id) FROM Book b WHERE length(b.title) > :number")
    int countByTitleIsGreaterThan(int number);

    // 11.
    Book findBookByTitle(String title);

    // 12.
    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.copies = b.copies + :amount WHERE b.releaseDate > :localDate")
    int updateCopies(LocalDate localDate, int amount);

    // 13.
    @Transactional
    int deleteByCopiesLessThan(int amount);
}
