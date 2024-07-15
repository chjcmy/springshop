package com.choi.springshop.application.service.Auth;

import com.choi.springshop.application.dto.Auth.SignUpRequest;

public interface AuthService {
    void registerUser(SignUpRequest signUpRequest);
}
