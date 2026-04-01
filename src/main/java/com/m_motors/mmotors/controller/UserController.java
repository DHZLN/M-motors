package com.mmotors.m_motors_app.controller;

import com.mmotors.m_motors_app.model.User;
import com.mmotors.m_motors_app.service.UserService;

public class UserController { // Pas @Controller pour l'instant
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String showRegistrationForm() {
        return "inscription";
    }

    public String registerUser(User user) {
        userService.inscrire(user);
        return "inscription-success"; // Ou redirection vers connexion
    }

    public String showLoginForm() {
        return "connexion";
    }
}