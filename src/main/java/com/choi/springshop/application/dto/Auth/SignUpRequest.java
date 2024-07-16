package com.choi.springshop.application.dto.Auth;

import com.choi.springshop.domain.model.valueobject.User.Address;
import com.choi.springshop.domain.model.valueobject.User.PhoneNumber;
import com.choi.springshop.domain.model.valueobject.User.UserEmail;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {
    private String username;
    private String password;
    @Valid
    private Address address;
    @Valid
    private PhoneNumber phoneNumber;
    @Valid
    private UserEmail email;


}