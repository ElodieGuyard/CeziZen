package com.example.cesizen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RespirationController {
    @GetMapping("/respiration")
    public String viewRespiration() {
        return "respiration";
    }
}
