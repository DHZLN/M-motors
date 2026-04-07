package com.m_motors.mmotors.repository;

import com.m_motors.mmotors.model.Dossier;
import com.m_motors.mmotors.model.StatutDossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {
    List<Dossier> findByClientId(Long clientId);
    List<Dossier> findByStatut(StatutDossier statut);
}