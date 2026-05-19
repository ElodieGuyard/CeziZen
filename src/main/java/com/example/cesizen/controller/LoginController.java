package com.example.cesizen.controller;

import com.example.cesizen.model.LoginRequest;
import com.example.cesizen.model.User;
import org.apache.coyote.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
//source : https://www.thymeleaf.org/doc/articles/springsecurity.html

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

//    @PostMapping(path="/auth") // Map ONLY POST Requests
//    public @ResponseBody ResponseEntity<String> checkLogin(@RequestBody LoginRequest request) {
//        String login = request.getLogin();
//        String password = request.getPassword();
//        return ResponseEntity.ok("Wouhou !");
//
//    }

/*    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }*/

}
