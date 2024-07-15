package com.choi.springshop;

import com.choi.springshop.application.dto.Auth.LoginRequest;
import com.choi.springshop.application.dto.Auth.SignUpRequest;
import com.choi.springshop.domain.model.valueobject.User.Address;
import com.choi.springshop.domain.model.valueobject.User.EmailAddress;
import com.choi.springshop.domain.model.valueobject.User.PhoneNumber;
import com.choi.springshop.domain.repository.User.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldAuthenticateUserAndReturnJwtToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\": \"testUser\", \"password\": \"testPassword\" }"))
                .andExpect(status().isOk());

    }

    @Test
    void shouldRegisterUser() throws Exception {

        Address address = new Address("서울시", "강남구", "테스트로", "한국", "12345");

        EmailAddress email = new EmailAddress("test@example.com");


        PhoneNumber phoneNumber = new PhoneNumber("+82", "1012345678");

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username("testUser")
                .password("testPassword")
                .address(address)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isOk());
    }
}
