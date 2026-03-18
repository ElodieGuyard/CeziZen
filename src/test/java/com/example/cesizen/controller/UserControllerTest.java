package com.example.cesizen.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    void contextLoads() {
        // Juste vérifier que Spring démarre
    }

    @Test
    void getAllUsers() throws Exception {
        mockMvc.perform(get("/api/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addNewUser() throws Exception {
        mockMvc.perform(post("/api/add")
                        .contentType("application/json")
                        .content("{}"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
