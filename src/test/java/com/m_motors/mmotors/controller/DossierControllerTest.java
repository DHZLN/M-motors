package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.Dossier;
import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.service.DossierService;
import com.m_motors.mmotors.service.UserService;
import com.m_motors.mmotors.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DossierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DossierService dossierService;

    @MockBean
    private UserService userService;

    @MockBean
    private VehicleService vehicleService;

    @Test
    @WithMockUser(username = "helene@test.fr", roles = "CLIENT")
    void showDossierForm_shouldDisplayFormWhenVehicleExists() throws Exception {
        Vehicle vehicule = Vehicle.builder()
                .id(1L)
                .marque("BMW")
                .modele("X1")
                .typeOffre(TypeOffre.LLD)
                .build();

        User user = User.builder()
                .id(1L)
                .email("helene@test.fr")
                .build();

        when(vehicleService.getVehiculeById(1L)).thenReturn(vehicule);
        when(userService.findByEmail("helene@test.fr")).thenReturn(user);

        mockMvc.perform(get("/dossiers/creer/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("dossier/creer"))
                .andExpect(model().attributeExists("dossier"))
                .andExpect(model().attributeExists("vehicule"));
    }

    @Test
    @WithMockUser(username = "helene@test.fr", roles = "CLIENT")
    void showDossierForm_shouldRedirectWhenVehicleNotFound() throws Exception {
        when(vehicleService.getVehiculeById(999L)).thenReturn(null);

        mockMvc.perform(get("/dossiers/creer/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/vehicules"));
    }

    @Test
    @WithMockUser(username = "helene@test.fr", roles = "CLIENT")
    void creerDossier_shouldCreateAndDisplayConfirmation() throws Exception {
        User user = User.builder()
                .id(1L)
                .email("helene@test.fr")
                .build();

        Vehicle vehicule = Vehicle.builder()
                .id(1L)
                .marque("Audi")
                .modele("Q5")
                .typeOffre(TypeOffre.ACHAT)
                .build();

        Dossier savedDossier = Dossier.builder()
                .id(10L)
                .client(user)
                .vehicule(vehicule)
                .typeOffre(TypeOffre.ACHAT)
                .build();

        when(userService.findByEmail("helene@test.fr")).thenReturn(user);
        when(vehicleService.getVehiculeById(1L)).thenReturn(vehicule);
        when(dossierService.creerDossier(any(Dossier.class))).thenReturn(savedDossier);

        mockMvc.perform(post("/dossiers/creer")
                        .with(csrf())
                        .param("vehicule.id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("dossier/confirmation"))
                .andExpect(model().attributeExists("dossier"))
                .andExpect(model().attributeExists("success"));
    }

    @Test
    @WithMockUser(username = "helene@test.fr", roles = "CLIENT")
    void creerDossier_shouldRedirectWhenUserOrVehicleMissing() throws Exception {
        when(userService.findByEmail("helene@test.fr")).thenReturn(null);

        mockMvc.perform(post("/dossiers/creer")
                        .with(csrf())
                        .param("vehicule.id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/vehicules"));
    }

    @Test
    @WithMockUser(username = "helene@test.fr", roles = "CLIENT")
    void clientDashboard_shouldDisplayUserDossiers() throws Exception {
        User user = User.builder()
                .id(1L)
                .email("helene@test.fr")
                .build();

        Dossier dossier = Dossier.builder()
                .id(100L)
                .build();

        when(userService.findByEmail("helene@test.fr")).thenReturn(user);
        when(dossierService.getDossiersByClientId(1L)).thenReturn(List.of(dossier));

        mockMvc.perform(get("/dossiers/mes-dossiers"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/dashboard"))
                .andExpect(model().attributeExists("dossiers"));
    }

    @Test
    @WithMockUser(username = "helene@test.fr", roles = "CLIENT")
    void clientDashboard_shouldRedirectToLoginWhenUserNotFound() throws Exception {
        when(userService.findByEmail("helene@test.fr")).thenReturn(null);

        mockMvc.perform(get("/dossiers/mes-dossiers"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}