package com.example.cesizen.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                    .authorizeHttpRequests(auth -> auth
                            // PUBLIC (non connecté)
                            .requestMatchers(
                                    "/",
                                    "/home",
                                    "/login",
                                    "/api/auth",
                                    "/register",
                                    "/CSS/**", "/js/**", "/images/**", // à modifier
                                    "/webjars/**",
                                    "/favicon.ico",
                                    "/api/**",
                                    "/sos"
                            ).permitAll()

                            // ADMIN (admin)
                            .requestMatchers("/admin/**").hasAnyRole("ADMINISTRATOR", "SUPERADMIN")

                            // CONNECTÉ (citizen, admin)
                            .requestMatchers("/app/**").authenticated()

                            // le reste: connecté (au début, c’est plus simple askip)
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
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
                "http://localhost:8100",  // Ionic dev
                "capacitor://localhost",  // Ionic sur appareil Android/iOS
                "ionic://localhost"       // Ionic sur appareil iOS (ancien)
        ));

        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Appliquer à toutes les routes
        return source;
    }


}
