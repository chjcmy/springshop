package com.choi.springshop.application.service.User;

import com.choi.springshop.application.dto.User.UpdateUserRequest;
import com.choi.springshop.application.dto.User.UserResponse;
import com.choi.springshop.domain.model.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

public interface UserService {

    UserResponse getUserByUsername(String username);

    void updateUser(String username, UpdateUserRequest updateUserRequest);
}
