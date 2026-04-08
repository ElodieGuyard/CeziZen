package com.example.cesizen.controller;

import com.example.cesizen.model.Role;
import com.example.cesizen.repository.ExerPersoCoherenceCardiaqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RespirationController {

    @Autowired
    private ExerPersoCoherenceCardiaqueRepository exerRepo;

    @GetMapping("/respiration")
    public String viewRespiration() {
        return "respiration";
    }

    @GetMapping("/respiration/{id:\\d+}")
    public String afficherExercice(Model model, @PathVariable Long id) {
        var opt = exerRepo.findById(id);
        if (opt.isEmpty()) {
            return "error";
        }

        model.addAttribute("exercice", opt.get());
        return "respiration";
    }

    @PostMapping("/respiration/congif"){
        @RequestParam Role role,
        return "redirect:/respiration/{id}";
    }
}
