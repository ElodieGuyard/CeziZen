//package com.example.cesizen.controller;
//
//import com.example.cesizen.model.User;
//import com.example.cesizen.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@SpringBootTest
//class UserControllerTest {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mockMvc;
//
//    @MockitoBean
//    UserRepository userRepository;  // FAKE DB, évite de charger le nécessaire à communication avec la vrai DB et d'écrire des données de tests dedans
//
//    @BeforeEach
//    void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//        //citation : "cet objet représente un point d’entrée au serveur MVC et de manipulation de différentes entrées REST de l’application.
//        //Cet objet est instancié a partir du contexte web crée précédemment par annotation (@WebAppConfiguration),
//        // du coup on aura besoin de récupérer cette instance par injection Spring"
//        //src : https://blog.zenika.com/2016/11/25/spring-mvc-test-dans-un-contexte-securise/
//    }
//
//    @Test
//    void getAllUsers() throws Exception {
//        mockMvc.perform(get("/api/all"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void addNewUser() throws Exception {
//
//        mockMvc.perform(post("/api/add") //Simule une vraie requête HTTP POST
//                        .contentType("application/json") // Son contenu sera au forma JSON
//                        .content("{\"name\":\"test\", \"role\":\"USER\", \"password\":\"test\"}")) //Voici son contneu
//                .andDo(print())
//                .andExpect(status().isOk()) //Vérifie le statut HTTP 200 OK
//                .andExpect(content().string("Saved"));
//
//        verify(userRepository).save(any(User.class)); // Vérifie que save() a été appelé 1x avec n'importe quel User
//    }
//
//    //TODO update user
//
//    @Test
//    void deleteUser() throws Exception {
//        mockMvc.perform(delete("/api/delete?Id=1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string("Deleted"));
//    }
//}
