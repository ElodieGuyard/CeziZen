package com.example.cesizen.controller;

import com.example.cesizen.model.Role;
import com.example.cesizen.model.User;
import com.example.cesizen.repository.ResourceRepository;
import com.example.cesizen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    //gestion ressources :


}
