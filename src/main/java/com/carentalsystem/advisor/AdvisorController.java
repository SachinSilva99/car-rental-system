package com.carentalsystem.advisor;


import com.carentalsystem.service.exception.DuplicationException;
import com.carentalsystem.util.enums.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class AdvisorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicationException.class)
    public ResponseEntity<StandardResponse> handleDuplicationException(DuplicationException e) {
        return new ResponseEntity<>(
                new StandardResponse(
                        400, e.getMessage(), null
                ),
                HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public ResponseEntity<StandardResponse> handleIOException(IOException e) {
        return new ResponseEntity<>(
                new StandardResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        null,
                        "Invalid JSON data"
                ),
                HttpStatus.BAD_REQUEST
        );
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<StandardResponse> handleClassNotFound(ClassNotFoundException e) {
        return new ResponseEntity<>(
                new StandardResponse(
                        404, e.getMessage(), null
                ),
                HttpStatus.NOT_FOUND);
    }
}
