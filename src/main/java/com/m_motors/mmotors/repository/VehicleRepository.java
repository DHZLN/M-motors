package com.mmotors.m_motors_app.repository;

import com.mmotors.m_motors_app.model.Vehicle;
import com.mmotors.m_motors_app.model.TypeOffre;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(Long id);
    List<Vehicle> findAll();
    List<Vehicle> rechercherVehicules(String marque, String modele, TypeOffre typeOffre);
}