package com.example.softunigamestore.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class GameDetailsDto implements Serializable {
    private String title;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public GameDetailsDto() {
    }

    public GameDetailsDto(String title, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDetailsDto entity = (GameDetailsDto) o;
        return Objects.equals(this.title, entity.title) &&
                Objects.equals(this.price, entity.price) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.releaseDate, entity.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, description, releaseDate);
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Price: " + price + "\n" +
                "Description: " + description + "\n" +
                "Release Date: " + releaseDate;
    }
}
