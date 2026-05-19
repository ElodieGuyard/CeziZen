package com.example.cesizen.controller;

import com.example.cesizen.model.ExerPersoCoherenceCardiaque;
import com.example.cesizen.model.User;
import com.example.cesizen.repository.ExerPersoCoherenceCardiaqueRepository;
import com.example.cesizen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class RespirationController {

    @Autowired
    private ExerPersoCoherenceCardiaqueRepository exerRepo;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/respiration")
    public String viewRespiration(Model model, Principal principal) {

        if (principal == null) {
            // pas connecté -> pas d'exercice en DB, la vue utilisera les valeurs par défaut
            model.addAttribute("exercice", null);
            return "respiration";
        }

        String nom = principal.getName(); // login = nom / Principal -> sécurity

        User user = userRepository.findUserBynom(nom)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable: " + nom));

        Long userId = user.getId(); // Long -> Integer

        ExerPersoCoherenceCardiaque ex = exerRepo.findByUtilisateurId(userId).orElse(null);
        model.addAttribute("exercice", ex);

        return "respiration";
    }

    @PostMapping("/respiration/config")
    public String enregistrerConfig(
            @RequestParam Integer dureeInspiration,
            @RequestParam Integer dureeApnee,
            @RequestParam Integer dureeExpiration,
            Principal principal //java security
    ) {
        String nom = principal.getName();

        User user = userRepository.findUserBynom(nom)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable: " + nom));

        Long userId = user.getId();

        ExerPersoCoherenceCardiaque ex = exerRepo.findByUtilisateurId(userId)
                .orElseGet(ExerPersoCoherenceCardiaque::new);

        ex.setIdUtilisateur(Math.toIntExact(userId));
        ex.setDureeInspiration(dureeInspiration);
        ex.setDureeApnee(dureeApnee);
        ex.setDureeExpiration(dureeExpiration);

        exerRepo.save(ex);

        return "redirect:/respiration";
    }
}
