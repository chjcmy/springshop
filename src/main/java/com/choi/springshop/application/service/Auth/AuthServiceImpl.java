package com.choi.springshop.application.service.Auth;

import com.choi.springshop.application.dto.Auth.SignUpRequest;
import com.choi.springshop.domain.model.entity.User;
import com.choi.springshop.domain.repository.User.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));



        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");  // 예시로 기본 역할 설정
        user.setRoles(roles);

        userRepository.save(user);
    }
}
