package com.example.usersystem.models;

import org.springframework.data.annotation.Transient;

import javax.persistence.*;

@Entity(name = "user_names")
public class UserName extends BaseEntity{

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Transient
    private String fullName;


    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "user_names_user",
            joinColumns = @JoinColumn(name = "user_name_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
