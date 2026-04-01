package com.mmotors.m_motors_app.service;

import com.mmotors.m_motors_app.model.Dossier;
import com.mmotors.m_motors_app.model.StatutDossier;
import com.mmotors.m_motors_app.repository.DossierRepository;
import java.util.List;

public class DossierService { // Pas @Service pour l'instant
    private final DossierRepository dossierRepository;

    public DossierService(DossierRepository dossierRepository) {
        this.dossierRepository = dossierRepository;
    }

    public Dossier creerDossier(Dossier dossier) {
        System.out.println("Création de dossier pour : " + dossier.getClient().getEmail());
        return null;
    }

    public List<Dossier> getDossiersByClientId(Long clientId) {
        return List.of();
    }

    public Dossier updateStatut(Long dossierId, StatutDossier nouveauStatut) {
        return null;
    }
}