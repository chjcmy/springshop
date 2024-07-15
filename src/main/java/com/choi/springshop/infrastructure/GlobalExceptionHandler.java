package com.choi.springshop.infrastructure;

import com.choi.springshop.domain.model.exception.ErrorResponse;
import com.choi.springshop.domain.model.exception.InvalidAddressException;
import com.choi.springshop.domain.model.exception.InvalidEmailException;
import com.choi.springshop.domain.model.exception.InvalidPhoneNumberException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmail(InvalidEmailException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponse("invalid_email", "올바른 이메일 형식을 입력해주세요."));
    }

    @ExceptionHandler(InvalidAddressException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAddress(InvalidEmailException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponse("invalid_address", "올바른 주소 형식을 입력해주세요. (예: '서울특별시 강남구 논현동 123-45'"));
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPhoneNumber(InvalidEmailException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponse("invalid_phone", "올바른 전화번호 형식을 입력해주세요. (예: '010-1234-5678')"));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append(", "));

        return ResponseEntity.badRequest().body(errorMessage.toString());
    }
}
