package com.mmotors.m_motors_app.service;

import com.mmotors.m_motors_app.model.User;
import com.mmotors.m_motors_app.repository.UserRepository;

public class UserService { // Pas @Service pour l'instant
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User inscrire(User user) {
        // Logique d'inscription plus tard
        System.out.println("Inscription de l'utilisateur : " + user.getEmail());
        return null;
    }

    public User findByEmail(String email) {
        // Recherche par email plus tard
        return null;
    }
}