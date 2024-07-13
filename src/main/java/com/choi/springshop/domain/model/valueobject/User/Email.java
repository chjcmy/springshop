package com.choi.springshop.domain.model.valueobject.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {
    private final String value;

    public Email(String value) {
        if (!value.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.value = value;
    }

}
