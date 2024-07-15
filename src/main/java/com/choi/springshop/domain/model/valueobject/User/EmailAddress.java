package com.choi.springshop.domain.model.valueobject.User;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;

@Getter
@Setter
public class EmailAddress {

    @Email
    private String address;

}
