package com.example.inventory.exception;

/**
 * Thrown when attempting to create a resource that already exists
 * (e.g. duplicate username, email, or category name).
 * Handled globally by GlobalExceptionHandler and mapped to HTTP 409.
 */
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
