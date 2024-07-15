package com.choi.springshop.interfaces.web.controller;

import com.choi.springshop.domain.model.entity.User;
import com.choi.springshop.application.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

}
