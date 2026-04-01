package com.mmotors.m_motors_app.service;

import com.mmotors.m_motors_app.model.Vehicle;
import com.mmotors.m_motors_app.model.TypeOffre;
import com.mmotors.m_motors_app.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // ← Ajoute cette annotation
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> rechercherVehicules(String marque, String modele, TypeOffre typeOffre) {
        System.out.println("Recherche de véhicules : " + marque + ", " + modele);
        return List.of(); // Liste vide pour l'instant
    }

    public Vehicle getVehiculeById(Long id) {
        return null;
    }

    public Vehicle saveVehicule(Vehicle vehicle) {
        return null;
    }
}