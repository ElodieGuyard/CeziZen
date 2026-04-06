package com.example.cesizen.controller.admin;

import com.example.cesizen.model.Categorie;
import com.example.cesizen.model.Resource;
import com.example.cesizen.model.Type;
import com.example.cesizen.repository.CategorieRepository;
import com.example.cesizen.repository.ResourceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/ressources")
public class AdminRessourcesController {

    private final ResourceRepository resourceRepository;
    private final CategorieRepository categorieRepository;

    public AdminRessourcesController(ResourceRepository resourceRepository,
                                     CategorieRepository categorieRepository) {
        this.resourceRepository = resourceRepository;
        this.categorieRepository = categorieRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("ressources", resourceRepository.findAll());
        return "admin/ressources";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("form", new ResourceForm());
        model.addAttribute("categories", categorieRepository.findAll());
        model.addAttribute("types", Type.values());
        return "admin/ressource_new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("form") ResourceForm form) {
        Categorie cat = categorieRepository.findById(form.getCategorieId())
                .orElseThrow(() -> new IllegalArgumentException("Categorie not found id=" + form.getCategorieId()));

        Resource r = new Resource();
        r.setCategorie(cat);
        r.setTitre(form.getTitre());
        r.setType(form.getType());
        r.setContenu(form.getContenu());
        // cree_le géré par @PrePersist
        resourceRepository.save(r);

        return "redirect:/admin/ressources";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Resource r = resourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource not found id=" + id));

        ResourceForm form = new ResourceForm();
        form.setTitre(r.getTitre());
        form.setType(r.getType());
        form.setContenu(r.getContenu());
        form.setCategorieId(r.getCategorie().getId());

        model.addAttribute("ressourceId", id);
        model.addAttribute("form", form);
        model.addAttribute("categories", categorieRepository.findAll());
        model.addAttribute("types", Type.values());
        return "admin/ressource_edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute("form") ResourceForm form) {
        Resource r = resourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource not found id=" + id));

        Categorie cat = categorieRepository.findById(form.getCategorieId())
                .orElseThrow(() -> new IllegalArgumentException("Categorie not found id=" + form.getCategorieId()));

        r.setCategorie(cat);
        r.setTitre(form.getTitre());
        r.setType(form.getType());
        r.setContenu(form.getContenu());
        r.setModifie_le(LocalDateTime.now());

        resourceRepository.save(r);
        return "redirect:/admin/ressources";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        resourceRepository.deleteById(id);
        return "redirect:/admin/ressources";
    }
}