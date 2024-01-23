package org.byovsiannikov.tasktheraven.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidateHandling {

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


}
