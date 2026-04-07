package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicules")
    public String rechercherVehicules(
            @RequestParam(required = false) String marque,
            @RequestParam(required = false) String modele,
            @RequestParam(required = false) TypeOffre typeOffre,
            Model model) {

        List<Vehicle> vehicules = vehicleService.rechercherVehicules(marque, modele, typeOffre);

        model.addAttribute("vehicules", vehicules);
        model.addAttribute("marque", marque);
        model.addAttribute("modele", modele);
        model.addAttribute("typeOffre", typeOffre);

        return "vehicules/liste";
    }

    @GetMapping("/vehicules/{id}")
    public String detailVehicule(@PathVariable Long id, Model model) {
        Vehicle vehicule = vehicleService.getVehiculeById(id);

        if (vehicule == null) {
            return "redirect:/vehicules";
        }

        model.addAttribute("vehicule", vehicule);
        return "vehicules/detail";
    }
}