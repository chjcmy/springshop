package com.choi.springshop.domain.model.valueobject.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumber {

    @NotBlank
    private String CountryCode;
    @NotBlank
    private String Number;

}