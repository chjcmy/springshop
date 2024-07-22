package com.choi.springshop.interfaces.web.controller;

import com.choi.springshop.application.dto.Auth.LoginRequest;
import com.choi.springshop.application.dto.Auth.LoginResponse;
import com.choi.springshop.application.dto.Auth.SignUpRequest;
import com.choi.springshop.application.service.Auth.AuthService;
import com.choi.springshop.infrastructure.jwt.JwtTokenProvider;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication.getName()); // 사용자 이름으로 토큰 생성

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);


        return ResponseEntity.ok().headers(headers).body("success");
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            authService.registerUser(signUpRequest);
            return ResponseEntity.ok().build();
        } catch (ConstraintViolationException e) {

            String errorMessage = e.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));

            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}
