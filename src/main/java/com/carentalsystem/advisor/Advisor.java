package com.carentalsystem.advisor;


import com.carentalsystem.service.exception.DuplicationException;
import com.carentalsystem.util.enums.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Advisor {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicationException.class)
    public ResponseEntity<StandardResponse> handleDuplicationException(DuplicationException e) {
        return new ResponseEntity<>(
                new StandardResponse(
                        400, null, null
                ),
                HttpStatus.BAD_REQUEST);
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
