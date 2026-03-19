package com.example.cesizen.controller;

import com.example.cesizen.model.Role;
import com.example.cesizen.model.User;
import com.example.cesizen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path="/users")
public class UserWebController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "create"; // template Thymeleaf  Endpoint final : /users/create
    }

    // Traiter la soumission du formulaire
    @PostMapping("/create") // Endpoint final : /users/create
    public String createUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        // Spring récupère AUTOMATIQUEMENT les valeurs du formulaire
        // et les mappe avec les propriétés de l'objet User

        user.setRole(Role.USER); // mettre le role user par défaut
        userRepository.save(user); // persister
        redirectAttributes.addFlashAttribute("message", "Utilisateur créé avec succès !");
        return "create";

    }

}
