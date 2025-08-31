package com.programming.services.exceptions;

public class CategoryNotFoundException extends Exception {
    private String categoryId;
    private String message;
    public CategoryNotFoundException(String categoryId, String message) {
        super(message);
        this.categoryId = categoryId;
    }
}
