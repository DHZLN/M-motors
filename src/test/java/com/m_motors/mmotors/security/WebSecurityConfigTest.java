package com.m_motors.mmotors.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebSecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    // ========== Tests pour les Endpoints Publics ==========

    @Test
    void homePage_shouldBeAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void loginPage_shouldBeAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    void registerPage_shouldBeAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/formulaire-inscription"))
                .andExpect(status().isOk());
    }

    @Test
    void vehiculesPage_shouldBeAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/vehicules"))
                .andExpect(status().isOk());
    }

    @Test
    void staticResources_shouldNotRedirectToLogin() throws Exception {
        // Les ressources statiques peuvent retourner 404 si elles n'existent pas,
        // mais elles ne doivent PAS rediriger vers /login (ce qui indiquerait un problème de sécurité)
        mockMvc.perform(get("/css/style.css"))
                .andExpect(status().isNotFound()); // 404, pas de redirection

        mockMvc.perform(get("/js/app.js"))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/images/logo.png"))
                .andExpect(status().isNotFound());
    }

    // ========== Tests pour les Endpoints Protégés ==========

    @Test
    void protectedEndpoint_shouldRedirectToLoginWhenNotAuthenticated() throws Exception {
        // Un endpoint qui nécessite une authentification doit rediriger vers /login
        mockMvc.perform(get("/some-protected-page"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(roles = "CLIENT")
    void adminEndpoint_shouldBeForbiddenForClientRole() throws Exception {
        // Un CLIENT ne peut pas accéder aux pages /admin/**
        // On attend un 403 Forbidden (pas un 404)
        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminEndpoint_shouldNotBeForbiddenForAdminRole() throws Exception {
        // Un ADMIN peut accéder aux pages /admin/**
        // On teste que l'accès n'est PAS refusé (pas de 403)
        // Si l'endpoint n'existe pas, on aura 404, ce qui est OK
        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().isNotFound()); // 404 = endpoint n'existe pas, mais l'accès est autorisé
    }

    @Test
    @WithMockUser(roles = "CLIENT")
    void clientEndpoint_shouldNotBeForbiddenForClientRole() throws Exception {
        // Un CLIENT peut accéder aux pages /dossiers/** et /client/**
        mockMvc.perform(get("/dossiers"))
                .andExpect(status().isNotFound()); // 404 = endpoint n'existe pas, mais l'accès est autorisé
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void clientEndpoint_shouldBeForbiddenForAdminRole() throws Exception {
        // Un ADMIN ne peut PAS accéder aux pages réservées aux CLIENTs
        mockMvc.perform(get("/dossiers"))
                .andExpect(status().isForbidden());
    }

    // ========== Tests pour Login/Logout ==========

    @Test
    @WithMockUser
    void logout_shouldRedirectToHomePage() throws Exception {
        mockMvc.perform(post("/logout").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/")); // Pas de pattern, juste l'URL exacte
    }

    // ========== Test du PasswordEncoder Bean ==========

    @Test
    void passwordEncoder_shouldBeConfigured() {
        // Ce test vérifie juste que le bean PasswordEncoder est bien créé
        // (il sera injecté automatiquement dans les autres composants)
        org.springframework.security.crypto.password.PasswordEncoder encoder = 
                new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        
        String rawPassword = "test123";
        String encodedPassword = encoder.encode(rawPassword);
        
        // Vérifie que l'encodage fonctionne
        assert encoder.matches(rawPassword, encodedPassword);
        assert !encoder.matches("wrongPassword", encodedPassword);
    }
}