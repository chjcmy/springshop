package com.choi.springshop.infrastructure;

import com.choi.springshop.domain.model.exception.ActiveCartAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ActiveCartAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleActiveCartAlreadyExistsException(ActiveCartAlreadyExistsException ex) {
        ErrorResponse error = new ErrorResponse("ACTIVE_CART_EXISTS", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("ENTITY_NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException exception)
    {
        Map<String,String>errorMap=new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error->
        {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }

    @Setter
    @Getter
    public static class ErrorResponse {
        // Getters and setters
        private String code;
        private String message;

        public ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }

    }
}