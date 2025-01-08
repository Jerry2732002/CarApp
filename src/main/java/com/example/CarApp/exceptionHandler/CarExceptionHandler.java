package com.example.CarApp.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CarExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> resolveException(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(error -> {
            String ex = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(ex,errorMessage);
        });


        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
