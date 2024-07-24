package com.choi.springshop.application.service.Auth;

import com.choi.springshop.application.dto.Auth.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    void registerUser(SignUpRequest signUpRequest);
}
