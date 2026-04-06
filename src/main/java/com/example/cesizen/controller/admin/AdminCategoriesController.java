package com.example.cesizen.controller.admin;

import com.example.cesizen.model.Categorie;
import com.example.cesizen.repository.CategorieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoriesController {

    private final CategorieRepository categorieRepository;

    public AdminCategoriesController(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categorieRepository.findAll());
        model.addAttribute("newCategorie", new Categorie());
        return "admin/categories";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("newCategorie") Categorie cat) {
        categorieRepository.save(cat);
        return "redirect:/admin/categories";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        categorieRepository.deleteById(id);
        return "redirect:/admin/categories";
    }
}