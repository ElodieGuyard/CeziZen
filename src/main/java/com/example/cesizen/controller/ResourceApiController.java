package com.example.cesizen.controller;

import com.example.cesizen.model.Resource;
import com.example.cesizen.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api/ressources")
public class ResourceApiController {

    @Autowired
    private ResourceRepository resourceRepository;

//    @GetMapping("/ressources.html")
//    public String showResources(Model model) {
//        Iterable<Resource> IterableResource = resourceRepository.findAll();
//        model.addAttribute("ressources", IterableResource);  // ← Passe la liste
//        return "ressources";  // templates/categories.html
//    }

    @GetMapping(path="/all")
    public Iterable<Resource> getAllResources() {
        return resourceRepository.findAll();
    }


    @PostMapping(path="/add") // Map ONLY POST Requests
    @ResponseBody
    public String addNewResource (@RequestBody Resource resource) {
        // @ResponseBody means the returned String is the response, not a view name
        resourceRepository.save(resource);
        return "Saved";
    }

}
