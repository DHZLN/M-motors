package com.m_motors.mmotors.service;

import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> rechercherVehicules(String marque, String modele, TypeOffre typeOffre) {
        return vehicleRepository.rechercherVehicules(
                (marque == null || marque.isBlank()) ? null : marque,
                (modele == null || modele.isBlank()) ? null : modele,
                typeOffre
        );
    }

    public List<Vehicle> getAllVehicules() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehiculeById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle saveVehicule(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}