package com.m_motors.mmotors.service;

import com.m_motors.mmotors.model.Dossier;
import com.m_motors.mmotors.model.StatutDossier;
import com.m_motors.mmotors.repository.DossierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DossierService {

    private final DossierRepository dossierRepository;

    public DossierService(DossierRepository dossierRepository) {
        this.dossierRepository = dossierRepository;
    }

    public List<Dossier> getAllDossiers() {
    return dossierRepository.findAll();
}

    public Dossier creerDossier(Dossier dossier) {
        dossier.setStatut(StatutDossier.EN_ATTENTE_DOCUMENTS);
        return dossierRepository.save(dossier);
    }

    public List<Dossier> getDossiersByClientId(Long clientId) {
        return dossierRepository.findByClientId(clientId);
    }

    public List<Dossier> getDossiersByStatut(StatutDossier statut) {
        return dossierRepository.findByStatut(statut);
    }

    public Dossier getDossierById(Long id) {
        return dossierRepository.findById(id).orElse(null);
    }

    public Dossier updateStatut(Long dossierId, StatutDossier nouveauStatut) {
        Dossier dossier = dossierRepository.findById(dossierId).orElse(null);

        if (dossier == null) {
            return null;
        }

        dossier.setStatut(nouveauStatut);
        return dossierRepository.save(dossier);
    }
}