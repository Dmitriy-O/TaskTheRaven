package org.byovsiannikov.tasktheraven.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionsHandling {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> handleInvalidMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
        {
            handleInvalidMap.put(error.getField(), error.getDefaultMessage());
        });
        return handleInvalidMap;
    }
    @ExceptionHandler(NoSuchElementException.class)
    public String handleEntityNotFound(NoSuchElementException e) {
       String errorResponse=e.getMessage();
        return  errorResponse;
    }

}