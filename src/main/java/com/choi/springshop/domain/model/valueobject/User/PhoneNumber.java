package com.choi.springshop.domain.model.valueobject.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumber {
    private final String value;

    public PhoneNumber(String value) {
        if (!value.matches("^\\+?[1-9]\\d{1,14}$")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        this.value = value;
    }

}