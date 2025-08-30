package com.programming.services.exceptions;

public class CategoryNotFoundException extends Exception {
    private String categoryId;
    public CategoryNotFoundException(String categoryId) {
        this.categoryId = categoryId;
    }
}
