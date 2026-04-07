package com.example.cesizen.controller;

import com.example.cesizen.controller.admin.ResourceForm;
import com.example.cesizen.controller.admin.UserForm;
import com.example.cesizen.model.Categorie;
import com.example.cesizen.model.Resource;
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

    @GetMapping("/user_new")
    public String newUserForm(Model model) {
        model.addAttribute("form", new UserForm());
        model.addAttribute("roles", Role.values());
        return "admin/user_new";
    }

    @PostMapping("/user_new")
    public String createUser(@ModelAttribute("form") UserForm form) {
        User user = new User();
        user.setName(form.getLogin());
        user.setMot_de_passe(passwordEncoder.encode(form.getPassword()));

        // convertir "ADMIN"/"USER" (String) en enum Role
        user.setRole(Role.valueOf(form.getRole()));

        if (form.isEnabled()) user.setEnabled();
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

    @PostMapping("/users/{id}/delete")
    public String delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }


}
