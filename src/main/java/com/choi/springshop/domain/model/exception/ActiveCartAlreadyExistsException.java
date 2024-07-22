package com.choi.springshop.domain.model.exception;

public class ActiveCartAlreadyExistsException extends RuntimeException {
    public ActiveCartAlreadyExistsException(String message) {
        super(message);
    }
}
