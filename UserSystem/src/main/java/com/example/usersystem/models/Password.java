package com.example.usersystem.models;

public @interface Password {
    int minLength();

    int maxLength();

    boolean containsDigit();

    boolean containsLowerCase();

    boolean containsUpperCase();

    boolean containsSpecial();

}
