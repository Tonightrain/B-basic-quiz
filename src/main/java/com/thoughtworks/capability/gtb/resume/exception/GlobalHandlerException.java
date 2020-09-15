package com.thoughtworks.capability.gtb.resume.exception;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(PersonIsNotExistException.class)
    public ResponseEntity<ErrorResult> handlePerson(PersonIsNotExistException ex){
        ErrorResult errorResult = ErrorResult.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .status(Response.SC_NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(EducationsNotExistException.class)
    public ResponseEntity<ErrorResult> handlePerson(EducationsNotExistException ex){
        ErrorResult errorResult = ErrorResult.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .status(Response.SC_NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

}
