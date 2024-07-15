package com.choi.springshop.domain.model.exception;

public class InvalidAddressException extends IllegalArgumentException {
    public InvalidAddressException(String message) {
        super(message);
    }
}
