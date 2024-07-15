package com.choi.springshop.domain.model.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ErrorResponse {
    private String errorCode;
    private String message;

    // Getter and Setter

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}