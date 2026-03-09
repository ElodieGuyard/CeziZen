package com.example.cesizen.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .formLogin(form -> form
                        .loginPage("/login")          // page login personnalisée
                        .failureUrl("/login?error")   // redirection si erreur
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/home")        // redirection après logout
                        .permitAll()
                );

        return http.build();
    }
}
