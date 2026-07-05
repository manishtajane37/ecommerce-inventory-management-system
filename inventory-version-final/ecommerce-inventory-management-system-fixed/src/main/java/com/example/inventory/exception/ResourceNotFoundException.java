package com.example.inventory.exception;

/**
 * Thrown when a requested entity (Product, Category, User) is not found in the database.
 * Handled globally by GlobalExceptionHandler and mapped to HTTP 404.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
