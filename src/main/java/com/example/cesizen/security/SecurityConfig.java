package com.example.cesizen.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
/*//POST Login
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

        return http.build();*/

/*            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/all").permitAll()
                            .anyRequest().authenticated()
                    );


            return http.build();
        }*/

        http
                .csrf(csrf -> csrf.disable())  // désactive CSRF
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // tout est accessible
        return http.build();
    }
}
