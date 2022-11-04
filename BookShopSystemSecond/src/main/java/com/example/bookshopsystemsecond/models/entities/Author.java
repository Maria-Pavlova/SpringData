package com.example.bookshopsystemsecond.models.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Set;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(targetEntity = Book.class, mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Book> books;


    public void addBook(Book book){
        this.books.add(book);
    }
}
