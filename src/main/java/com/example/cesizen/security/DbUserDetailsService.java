package com.example.cesizen.security;

import com.example.cesizen.model.User;
import com.example.cesizen.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DbUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findUserBynom(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // spring attend des rôles style "ROLE_USER"
        String role = "ROLE_" + u.getRole().name();

        return new org.springframework.security.core.userdetails.User(
                u.getName(),
                u.getMot_de_passe(), // doit être le hash BCrypt stocké en DB
                u.isEnabled(), true, true, true,
                List.of(new SimpleGrantedAuthority(role))
        );
    }
}
