package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String inscription(Model model) {
        model.addAttribute("user", new User());
        return "inscription";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}