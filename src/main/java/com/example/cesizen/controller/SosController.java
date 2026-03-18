package com.example.cesizen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SosController {
    @GetMapping("/sos")
    public String sos() {
        return "sos";
    }
}
