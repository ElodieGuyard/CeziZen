package com.example.cesizen.controller;

import com.example.cesizen.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AdminController {

    @Autowired
    private ResourceRepository resourceRepository;

    public String admin() {
        return "dashboard";
    }

    @GetMapping
    public String showRessources(Model model) {
        model.addAttribute("ressources", resourceRepository.findAll());
        return "admin/ressources";
    }
}
