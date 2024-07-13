package com.choi.springshop.interfaces.web.controller;

import com.choi.springshop.domain.model.entity.User;
import com.choi.springshop.interfaces.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 정보 조회 (아이디 기반)
    @GetMapping("/users/{userId}")
    public Mono<User> getUser(@PathVariable Long userId) {
        // 데이터베이스 또는 서비스에서 사용자 정보를 가져오는 로직 구현
        return userService.findById(userId);
    }

    // 새로운 사용자 생성
    @PostMapping("/users")
    public Mono<User> createUser(@RequestBody User user) {
        // 사용자 생성 로직 구현 (데이터베이스 저장 등)
        return userService.createUser(user);
    }

}
