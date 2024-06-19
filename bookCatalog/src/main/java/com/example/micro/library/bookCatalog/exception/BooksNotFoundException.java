package com.example.micro.library.bookCatalog.exception;

public class BooksNotFoundException extends RuntimeException {
    public BooksNotFoundException(String message) {
        super(message);
    }
}
