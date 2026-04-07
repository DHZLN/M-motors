package com.m_motors.mmotors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String inscription() {
        return "inscription";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}