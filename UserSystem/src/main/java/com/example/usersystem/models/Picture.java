package com.example.usersystem.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String caption;

    @Column(nullable = false)
    private String path;

    @ManyToMany
    private Set<Album> albums;


}