package com.choi.springshop.domain.model.valueobject.User;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;

@Getter
@Setter
public class EmailAddress {

    @Email(message = ("정확하지 않은 이메일 입니다"))
    private String address;

}
