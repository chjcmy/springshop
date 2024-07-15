package com.choi.springshop.domain.model.valueobject.User;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber {

    @NotBlank
    private String CountryCode;
    @NotBlank
    private String Number;

}