package com.example.bookshopsystemsecond.models.entities;

import com.example.bookshopsystemsecond.models.enums.AgeRestriction;
import com.example.bookshopsystemsecond.models.enums.EditionType;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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


}
