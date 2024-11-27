package com.educandoweb.course.exception;

import com.educandoweb.course.exception.payment.PaymentNotFoundException;
import com.educandoweb.course.exception.users.EmailAlredyExistException;
import com.educandoweb.course.exception.users.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex){
        Map<String, Object> body = new HashMap<>();

        body.put("status", 404);
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(EmailAlredyExistException.class)
    public ResponseEntity<Map<String, Object>> handleEmailAlreadyExistException (EmailAlredyExistException ex){
        Map<String, Object> body = new HashMap<>();

        body.put("status", 409);
        body.put("error", "Conflict");
        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());


        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handlePaymentNotFoundException (PaymentNotFoundException ex){
        Map<String, Object> body = new HashMap<>();

        body.put("status", 404);
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
