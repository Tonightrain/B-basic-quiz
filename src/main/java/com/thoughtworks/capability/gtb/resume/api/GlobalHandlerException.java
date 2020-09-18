package com.thoughtworks.capability.gtb.resume.api;

import com.thoughtworks.capability.gtb.resume.exception.ErrorResult;
import com.thoughtworks.capability.gtb.resume.exception.PersonIsNotExistException;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handleException(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResult errorResult = ErrorResult.builder()
                .message(message)
                .timestamp(new Date())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .status(Response.SC_BAD_REQUEST)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }


}
