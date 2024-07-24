package com.choi.springshop.interfaces.web.controller;

import com.choi.springshop.application.dto.User.UpdateUserRequest;
import com.choi.springshop.application.dto.User.UserResponse;
import com.choi.springshop.application.service.User.UserService;
import com.choi.springshop.domain.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getCurrentUser(Authentication authentication) {
        return userService.getUserByUsername(authentication.getName());
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateUser(
            @RequestBody UpdateUserRequest updateUserRequest,
            Authentication authentication) {
        try {
            userService.updateUser(authentication.getName(), updateUserRequest);
            return ResponseEntity.ok().build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
