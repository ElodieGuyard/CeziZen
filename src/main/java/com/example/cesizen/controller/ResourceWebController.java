package com.example.cesizen.controller;

import com.example.cesizen.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/ressources")
public class ResourceWebController {
//TODO : requiert une authentification pour add ou modify
    @Autowired
    private ResourceRepository resourceRepository;

    @GetMapping
    public String showRessources(Model model) {
        model.addAttribute("ressources", resourceRepository.findAll());
        return "ressources";
    }
}