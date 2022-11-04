package com.example.bookshopsystemsecond.models.entities;

import lombok.*;

import javax.persistence.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


}
