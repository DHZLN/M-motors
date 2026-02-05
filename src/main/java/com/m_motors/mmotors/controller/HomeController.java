package com.m_motors.m_motors_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // Thymeleaf va chercher index.html dans /templates
    }
}
