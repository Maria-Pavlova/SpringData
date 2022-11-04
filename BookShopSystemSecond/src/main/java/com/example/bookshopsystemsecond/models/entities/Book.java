package com.example.bookshopsystemsecond.models.entities;

import com.example.bookshopsystemsecond.models.enums.AgeRestriction;
import com.example.bookshopsystemsecond.models.enums.EditionType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book extends BaseEntity{

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Enumerated(EnumType.ORDINAL)
    private AgeRestriction ageRestriction;

    @ManyToOne
    private Author author;

    @ManyToMany
    private Set<Category> categories;


//    public Book() {
//    }
//
//    public Book(EditionType editionType, LocalDate releaseDate, Integer copies,
//                BigDecimal price, AgeRestriction ageRestriction, String title,
//                Author author, Set<Category> categories) {
//        this.editionType = editionType;
//        this.releaseDate = releaseDate;
//        this.copies = copies;
//        this.price = price;
//        this.ageRestriction = ageRestriction;
//        this.title = title;
//        this.author = author;
//        this.categories = categories;
//    }


//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public EditionType getEditionType() {
//        return editionType;
//    }
//
//    public void setEditionType(EditionType editionType) {
//        this.editionType = editionType;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public Integer getCopies() {
//        return copies;
//    }
//
//    public void setCopies(Integer copies) {
//        this.copies = copies;
//    }
//
//    public LocalDate getReleaseDate() {
//        return releaseDate;
//    }
//
//    public void setReleaseDate(LocalDate releaseDate) {
//        this.releaseDate = releaseDate;
//    }
//
//    public AgeRestriction getAgeRestriction() {
//        return ageRestriction;
//    }
//
//    public void setAgeRestriction(AgeRestriction ageRestriction) {
//        this.ageRestriction = ageRestriction;
//    }
//
//    public Author getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Author author) {
//        this.author = author;
//    }
//
//    public Set<Category> getCategories() {
//        return Collections.unmodifiableSet(categories);
//    }
//
//    public void setCategories(Set<Category> categories) {
//        this.categories = categories;
//    }
}
