package com.m_motors.mmotors.repository;

import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("""
           SELECT v FROM Vehicle v
           WHERE (:marque IS NULL OR LOWER(v.marque) LIKE LOWER(CONCAT('%', :marque, '%')))
             AND (:modele IS NULL OR LOWER(v.modele) LIKE LOWER(CONCAT('%', :modele, '%')))
             AND (:typeOffre IS NULL OR v.typeOffre = :typeOffre)
           """)
    List<Vehicle> rechercherVehicules(@Param("marque") String marque,
                                      @Param("modele") String modele,
                                      @Param("typeOffre") TypeOffre typeOffre);
}