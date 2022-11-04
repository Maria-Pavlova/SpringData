package com.example.bookshopsystemsecond.services.impl;

import com.example.bookshopsystemsecond.models.entities.Author;
import com.example.bookshopsystemsecond.repositories.AuthorRepository;
import com.example.bookshopsystemsecond.repositories.BookRepository;
import com.example.bookshopsystemsecond.services.AuthorService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void seedAuthors(List<Author> authors){
        authorRepository.saveAll(authors);
    }

    @Override
    public boolean isDataSeeded() {
        return authorRepository.count() > 0;

    }

    @Override
    public Author getRandomAuthor() {
        long randomId = new Random().nextLong(1L, authorRepository.count() + 1);
        return this.authorRepository.findById(randomId).orElseThrow(NoSuchElementException::new);
    }

    // 03.
    @Override
    public void getAllAuthorsOrderByCountOfBooks() {
        this.authorRepository
                .findAllByBooksSizeDesc()
                .stream()
                .map(a -> String.format("%s %s %d",
                        a.getFirstName(), a.getLastName(),
                        a.getBooks().size()))
                .toList()
                .forEach(System.out::println);

    }


}
