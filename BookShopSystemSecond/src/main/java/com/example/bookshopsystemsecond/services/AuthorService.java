package com.example.bookshopsystemsecond.services;

import com.example.bookshopsystemsecond.models.entities.Author;
import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors(List<Author> authors) throws IOException;

    boolean isDataSeeded();

    Author getRandomAuthor();

    void getAllAuthorsOrderByCountOfBooks();
}
