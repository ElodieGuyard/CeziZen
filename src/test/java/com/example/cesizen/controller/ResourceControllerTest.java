//package com.example.cesizen.controller;
//
//import com.example.cesizen.model.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//public class ResourceControllerTest {
//
//    @Autowired
//    private WebApplicationContext context;
//
////    @Test
////    void addNewRessource() throws Exception {
////
////        mockMvc.perform(post("/api/add") //Simule une vraie requête HTTP POST
////                        .contentType("application/json") // Son contenu sera au forma JSON
////                        .content("{\"name\":\"test\", \"role\":\"USER\", \"password\":\"test\"}")) //Voici son contneu
////                .andDo(print())
////                .andExpect(status().isOk()) //Vérifie le statut HTTP 200 OK
////                .andExpect(content().string("Saved"));
////
////        verify(userRepository).save(any(User.class)); // Vérifie que save() a été appelé 1x avec n'importe quel User
////    }
//}
