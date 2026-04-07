package com.example.cesizen.controller;

import com.example.cesizen.model.Role;
import com.example.cesizen.model.User;
import com.example.cesizen.repository.ResourceRepository;
import com.example.cesizen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/admin")
public class AdminController {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping({"", "/"})
    public String adminHome() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/dashboard")
    public String admin() {
        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }

    @GetMapping("/users/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        return "admin/user_new";
    }

    @PostMapping("/users/new")
    public String createUser(
            @RequestParam String nom,
            @RequestParam Role role,
            @RequestParam String mot_de_passe,
            @RequestParam(name = "enabled", defaultValue = "false") boolean enabled
    ) {
        User user = new User();
        user.setName(nom);
        user.setRole(role);
        user.setMot_de_passe(passwordEncoder.encode(mot_de_passe));
        if (enabled) user.setEnabled();
        else user.setDisable();

        userRepository.save(user);
        return "redirect:/admin/users";
    }


    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found id=" + id));

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values()); // pour remplir le <select>
        return "admin/user_edit";
    }

    @PostMapping("/users/{id}/edit")
    public String updateUser(
            @PathVariable Long id,
            @RequestParam Role role,
            @RequestParam(name = "enabled", defaultValue = "false") boolean enabled
    ) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found id=" + id));

        user.setRole(role);
        if (enabled) user.setEnabled();
        else user.setDisable();

        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("users/{id}/delete")
    public String delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }


}
