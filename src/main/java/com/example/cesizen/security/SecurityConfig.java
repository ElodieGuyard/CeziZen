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
                    .authorizeHttpRequests(auth -> auth
                            // PUBLIC (non connecté)
                            .requestMatchers(
                                    "/",
                                    "/home",
                                    "/login",
                                    "/register",
                                    "/CSS/**", "/js/**", "/images/**", // à modifier
                                    "/webjars/**",
                                    "/favicon.ico",
                                    "/api/**"
                            ).permitAll()

                            // SUPER ADMIN
                            .requestMatchers("/super-admin/**").hasRole("SUPERADMIN") // à adapter

                            // ADMIN (admin + super admin)
                            .requestMatchers("/admin/**").hasAnyRole("ADMINISTRATOR", "SUPERADMIN")

                            // CONNECTÉ (citizen, moderator, admin, super_admin)
                            .requestMatchers("/app/**").authenticated()

                            // le reste: connecté (au début, c’est plus simple)
                            .anyRequest().authenticated()
                    )
                    .formLogin(form -> form
                            .loginPage("/login")
                            .defaultSuccessUrl("/home", true)
                            .permitAll()
                    )
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/home"))

                    .csrf(csrf -> csrf
                            .ignoringRequestMatchers("/api/**") // Les API REST n'utilisent pas de session/CSRF
                    );


        return http.build();
        }
    }
