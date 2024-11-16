package com.educandoweb.course.exception.users;

public class EmailAlredyExistException extends RuntimeException{

    public EmailAlredyExistException(String message) {
        super(message);
    }
}
