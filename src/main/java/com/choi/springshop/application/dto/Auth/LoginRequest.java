package com.choi.springshop.application.dto.Auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
