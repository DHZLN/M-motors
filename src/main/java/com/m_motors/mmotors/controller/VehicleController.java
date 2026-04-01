package com.mmotors.m_motors_app.controller;

import com.mmotors.m_motors_app.service.VehicleService;

public class VehicleController { // Pas @Controller pour l'instant
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public String rechercherVehicules(String marque, String modele) {
        return "vehicules/liste";
    }

    public String detailVehicule(Long id) {
        return "vehicules/detail";
    }
}