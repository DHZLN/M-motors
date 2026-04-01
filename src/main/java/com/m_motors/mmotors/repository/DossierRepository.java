package com.mmotors.m_motors_app.repository;

import com.mmotors.m_motors_app.model.Dossier;
import com.mmotors.m_motors_app.model.StatutDossier;
import java.util.List;
import java.util.Optional;

public interface DossierRepository {
    Dossier save(Dossier dossier);
    Optional<Dossier> findById(Long id);
    List<Dossier> findByClientId(Long clientId);
    List<Dossier> findByStatut(StatutDossier statut);
}