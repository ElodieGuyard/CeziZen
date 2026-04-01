package com.example.cesizen.controller;

import com.example.cesizen.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ResourceRepository resourceRepository;

    @GetMapping("/")
    public String root() {
        return "home";
    }

    @GetMapping("/home")
    public String viewHome(Model model){
        model.addAttribute("ressources", resourceRepository.findAll());
        return "home";
    }
}