package com.choi.springshop.interfaces.service.User;

import com.choi.springshop.domain.model.entity.User;
import reactor.core.publisher.Mono;

public class UserServicedImpl implements UserService{
    @Override
    public Mono<User> findById(Long userId) {
        return null;
    }

    @Override
    public Mono<User> createUser(User user) {
        return null;
    }
}
