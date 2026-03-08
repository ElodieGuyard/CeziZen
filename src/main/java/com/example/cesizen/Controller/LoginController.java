package com.example.cesizen.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
//source : https://www.thymeleaf.org/doc/articles/springsecurity.html
    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .formLogin()
//                .loginPage("/login.html")
//                .failureUrl("/login-error.html")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/home.html");
//    }

}
