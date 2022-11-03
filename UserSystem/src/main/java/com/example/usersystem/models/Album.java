package com.example.usersystem.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "albums")
public class Album extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(name = "background_color",nullable = false)
    private String backgroundColor;

    @Column(nullable = false)
    private boolean isPublic;

    @ManyToMany
    private Set<Picture> pictures;
    

    public Album(String name, String backgroundColor, boolean isPublic, Set<Picture> pictures) {
        this.name = name;
        this.backgroundColor = backgroundColor;
        this.isPublic = isPublic;
        this.pictures = pictures;

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

}
