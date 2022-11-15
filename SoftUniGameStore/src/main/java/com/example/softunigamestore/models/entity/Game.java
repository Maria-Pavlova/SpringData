package com.example.softunigamestore.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "games")
public class Game extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String trailer;

    @Column(name = "image_thumbnail", nullable = false)
    private String imageThumbnail;

    @Column(nullable = false)
    private Double size;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;



    public Game() {
    }

    public Game(String title, String trailer, String imageThumbnail, Double size, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return
                "Title: " + title + '\n' +
                "Price: " + price + '\n'+
                "Description: " + description + '\n' +
                "Release date: " + releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return title.equals(game.title) && trailer.equals(game.trailer) && imageThumbnail.equals(game.imageThumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, trailer, imageThumbnail);
    }
}
