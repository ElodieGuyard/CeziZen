package com.example.cesizen.controller;

import com.example.cesizen.model.Categorie;
import com.example.cesizen.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategorieRepository categorieRepository;
    //TODO : requiert une authentification pour add ou modify
    @GetMapping("/categories")
    public String listerCategories(Model model) {
        Iterable<Categorie> IterableCategories = categorieRepository.findAll();
        model.addAttribute("categories", IterableCategories);  // ← Passe la liste
        return "categories";  // templates/categories.html
    }
}

