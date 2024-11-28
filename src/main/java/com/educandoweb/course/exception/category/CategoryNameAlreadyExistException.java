package com.educandoweb.course.exception.category;

public class CategoryNameAlreadyExistException extends RuntimeException{

    public CategoryNameAlreadyExistException(String message) {
        super(message);
    }
}
