package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.Dossier;
import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.service.DossierService;
import com.m_motors.mmotors.service.UserService;
import com.m_motors.mmotors.service.VehicleService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dossiers")
public class DossierController {

    private final DossierService dossierService;
    private final UserService userService;
    private final VehicleService vehicleService;

    public DossierController(DossierService dossierService, UserService userService, VehicleService vehicleService) {
        this.dossierService = dossierService;
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/creer/{vehiculeId}")
    public String showDossierForm(@PathVariable Long vehiculeId, Model model, Authentication authentication) {
        Vehicle vehicule = vehicleService.getVehiculeById(vehiculeId);

        if (vehicule == null) {
            return "redirect:/vehicules";
        }

        User user = userService.findByEmail(authentication.getName());

        Dossier dossier = new Dossier();
        dossier.setVehicule(vehicule);
        dossier.setClient(user);
        dossier.setTypeOffre(vehicule.getTypeOffre());

        model.addAttribute("dossier", dossier);
        model.addAttribute("vehicule", vehicule);

        return "dossier/creer";
    }

    @PostMapping("/creer")
    public String creerDossier(@ModelAttribute Dossier dossier, Authentication authentication, Model model) {
        User user = userService.findByEmail(authentication.getName());
        Vehicle vehicule = vehicleService.getVehiculeById(dossier.getVehicule().getId());

        if (user == null || vehicule == null) {
            return "redirect:/vehicules";
        }

        dossier.setClient(user);
        dossier.setVehicule(vehicule);
        dossier.setTypeOffre(vehicule.getTypeOffre());

        Dossier savedDossier = dossierService.creerDossier(dossier);

        model.addAttribute("dossier", savedDossier);
        model.addAttribute("success", "Votre dossier a bien été créé.");

        return "dossier/confirmation";
    }

    @GetMapping("/mes-dossiers")
    public String clientDashboard(Authentication authentication, Model model) {
        User user = userService.findByEmail(authentication.getName());

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("dossiers", dossierService.getDossiersByClientId(user.getId()));
        return "client/dashboard";
    }
}