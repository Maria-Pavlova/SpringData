package com.example.softunigamestore.exceptions;

public class ExistingGameException extends RuntimeException{

    public ExistingGameException() {
    }

    public ExistingGameException(String message) {
        super(message);
    }

    public ExistingGameException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistingGameException(Throwable cause) {
        super(cause);
    }
}
