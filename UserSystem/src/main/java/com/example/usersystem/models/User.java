package com.example.usersystem.models;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "user_name", nullable = false)
    @Size(min = 4, max = 30)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Transient
    private String fullName;

    @javax.validation.constraints.Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,50}$", message = "Invalid password")
    @NotNull
    private String password;

    @Email(message = "Invalid email")
    private String email;

    @Column(name = "registered_on", nullable = false)
    private LocalDateTime registeredOn;

    @Column(name = "last_time_logged_in")
    private LocalDateTime lastTimeLoggedIn;

    @Column(length = 120)
    private int age;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToMany
    private Set<User> friends;

    @ManyToOne
    private Town bornTown;

    @ManyToOne
    private Town currentlyLivingIn;

    @OneToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;

    @OneToMany
    private Set<Album> albums;

    public User() {
    }

    public User(String username, String firstName, String lastName, String password, String email, LocalDateTime registeredOn, LocalDateTime lastTimeLoggedIn, int age, boolean isDeleted) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.registeredOn = registeredOn;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
        this.age = age;
        this.isDeleted = false;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = this.firstName + " " + this.lastName;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new ValidationException("Invalid email!");
        }
        this.email = email;
        return email;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDateTime getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDateTime lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public Town getCurrentlyLivingIn() {
        return currentlyLivingIn;
    }

    public void setCurrentlyLivingIn(Town currentlyLivingIn) {
        this.currentlyLivingIn = currentlyLivingIn;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static boolean isValidEmail(String enteredEmail) {
        String EMAIL_REGEX = "^([a-zA-Z0-9]+[_\\-\\.]*[a-zA-Z0-9]+)@([a-zA-Z0-9]+[\\-]*[a-zA-Z0-9]+\\.[a-z]+\\.*[a-z]*)$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(enteredEmail);
        return ((!enteredEmail.isEmpty()) && (enteredEmail != null) && (matcher.matches()));
    }

}
