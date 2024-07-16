package com.choi.springshop;

import com.choi.springshop.domain.repository.User.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldLoginAndGetUserInfo() throws Exception {

            mockMvc.perform(get("/api/products")
                            .param("name", "베이직 티셔츠")  // Name changed to match productDTO
                            .param("minPrice", "5000")
                            .param("maxPrice", "15000")
                            .param("categoryId", "1")
                            .param("page", "0")
                            .param("size", "10"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[0].name").value("베이직 티셔츠"))  // Name assertion updated
                    .andExpect(jsonPath("$.content[0].price").value(15000))
                    .andExpect(jsonPath("$.totalElements").value(1));

    }








}
