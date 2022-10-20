package com.example.usersystem.models;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "albums")
public class Album extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(name = "background_color",nullable = false)
    private String backgroundColor;

    @Column(nullable = false)
    private boolean isPublic;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "albums")
    private Set<Picture> pictures = new java.util.LinkedHashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Album(String name, String backgroundColor, boolean isPublic, Set<Picture> pictures, User user) {
        this.name = name;
        this.backgroundColor = backgroundColor;
        this.isPublic = isPublic;
        this.pictures = pictures;
        this.user = user;
    }

    public Album() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
