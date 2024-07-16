package com.choi.springshop.application.service.User;

import com.choi.springshop.application.dto.User.UpdateUserRequest;
import com.choi.springshop.application.dto.User.UserResponse;
import com.choi.springshop.domain.model.entity.User;
import com.choi.springshop.domain.repository.User.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServicedImpl implements UserService{
    private final UserRepository userRepository;

    public UserServicedImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserResponse(
                user.getUsername(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getAddress()
        );
    }

    @Override
    @Transactional
    public void updateUser(String username, UpdateUserRequest updateUserRequest) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        if (updateUserRequest.getPhoneNumber() != null) {
            user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        }
        if (updateUserRequest.getEmail() != null) {
            user.setEmail(updateUserRequest.getEmail());
        }
        if (updateUserRequest.getAddress() != null) {
            user.setAddress(updateUserRequest.getAddress());
        }
    }
}
