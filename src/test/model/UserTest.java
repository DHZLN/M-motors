package com.m_motors.mmotors.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void user_getters_setters() {
        User user = new User();
        user.setEmail("test@test.fr");
        user.setNom("Dupont");
        user.setPrenom("Jean");
        user.setPassword("123");
        user.setRole("CLIENT");
        user.setEnabled(true);

        assertEquals("test@test.fr", user.getEmail());
        assertEquals("Dupont", user.getNom());
        assertEquals("Jean", user.getPrenom());
        assertEquals("123", user.getPassword());
        assertEquals("CLIENT", user.getRole());
        assertTrue(user.isEnabled());
    }
}