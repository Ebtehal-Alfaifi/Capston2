package com.example.maintaincesystem.ControllerAdvise;

import com.example.maintaincesystem.ApiResponse.ApiException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.PatternSyntaxException;

@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException e){
        String message= e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
        String message= e.getMessage();
        return ResponseEntity.status(400).body(message);
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationException(ConstraintViolationException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);

    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }


    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }


    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class)
    public ResponseEntity InvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e){
        String message = e.getMessage();
        return ResponseEntity.status(200).body(message);
    }


    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException(NoResourceFoundException e){
        String message= e.getMessage();
        return ResponseEntity.status(400).body(message);
    }



    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }



@ExceptionHandler(value = HttpMessageNotReadableException.class)
public ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException e){
        String message= e.getMessage();
        return ResponseEntity.status(400).body(message);
}


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }


    @ExceptionHandler(value = PatternSyntaxException.class)
    public ResponseEntity handlePatternSyntaxException(PatternSyntaxException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);

    }


    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException e) {
        String message = e.getMessage();
        return ResponseEntity.status(404).body(message);
    }






}
