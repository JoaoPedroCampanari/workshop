package com.educandoweb.course.exception.products;

public class ProductNameAlreadyExistException extends RuntimeException{
    public ProductNameAlreadyExistException(String message) {
        super(message);
    }
}
