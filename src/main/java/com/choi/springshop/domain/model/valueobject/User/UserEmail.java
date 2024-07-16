package com.choi.springshop.domain.model.valueobject.User;

import jakarta.persistence.Embeddable;
import lombok.*;
import jakarta.validation.constraints.Email;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserEmail {

    @Email(message = ("정확하지 않은 이메일 입니다"))
    private String email;

}
