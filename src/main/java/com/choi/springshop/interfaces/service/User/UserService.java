package com.choi.springshop.interfaces.service.User;

import com.choi.springshop.domain.model.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface UserService {
    Mono<User> findById(Long userId);

    Mono<User> createUser(User user);
}
