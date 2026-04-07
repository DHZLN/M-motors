package com.m_motors.mmotors.service;

import com.m_motors.mmotors.model.Dossier;
import com.m_motors.mmotors.model.StatutDossier;
import com.m_motors.mmotors.repository.DossierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DossierServiceTest {

    private DossierRepository dossierRepository;
    private DossierService dossierService;

    @BeforeEach
    void setUp() {
        dossierRepository = mock(DossierRepository.class);
        dossierService = new DossierService(dossierRepository);
    }

    @Test
    void getAllDossiers_shouldReturnAll() {
        Dossier d1 = Dossier.builder().id(1L).build();
        Dossier d2 = Dossier.builder().id(2L).build();

        when(dossierRepository.findAll()).thenReturn(List.of(d1, d2));

        List<Dossier> result = dossierService.getAllDossiers();

        assertEquals(2, result.size());
        verify(dossierRepository, times(1)).findAll();
    }

    @Test
    void creerDossier_shouldSetDefaultStatusAndSave() {
        Dossier dossier = Dossier.builder().build();

        when(dossierRepository.save(any(Dossier.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Dossier saved = dossierService.creerDossier(dossier);

        assertNotNull(saved);
        assertEquals(StatutDossier.EN_ATTENTE_DOCUMENTS, saved.getStatut());

        verify(dossierRepository, times(1)).save(dossier);
    }

    @Test
    void getDossiersByClientId_shouldReturnList() {
        Dossier d1 = Dossier.builder().id(1L).build();
        Dossier d2 = Dossier.builder().id(2L).build();

        when(dossierRepository.findByClientId(1L)).thenReturn(List.of(d1, d2));

        List<Dossier> result = dossierService.getDossiersByClientId(1L);

        assertEquals(2, result.size());
        verify(dossierRepository, times(1)).findByClientId(1L);
    }

    @Test
    void getDossiersByStatut_shouldReturnList() {
        Dossier d1 = Dossier.builder()
                .id(1L)
                .statut(StatutDossier.ACCEPTE)
                .build();

        when(dossierRepository.findByStatut(StatutDossier.ACCEPTE)).thenReturn(List.of(d1));

        List<Dossier> result = dossierService.getDossiersByStatut(StatutDossier.ACCEPTE);

        assertEquals(1, result.size());
        assertEquals(StatutDossier.ACCEPTE, result.get(0).getStatut());

        verify(dossierRepository, times(1)).findByStatut(StatutDossier.ACCEPTE);
    }

    @Test
    void getDossierById_shouldReturnDossierWhenFound() {
        Dossier dossier = Dossier.builder().id(1L).build();

        when(dossierRepository.findById(1L)).thenReturn(Optional.of(dossier));

        Dossier result = dossierService.getDossierById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(dossierRepository, times(1)).findById(1L);
    }

    @Test
    void getDossierById_shouldReturnNullWhenNotFound() {
        when(dossierRepository.findById(99L)).thenReturn(Optional.empty());

        Dossier result = dossierService.getDossierById(99L);

        assertNull(result);
        verify(dossierRepository, times(1)).findById(99L);
    }

    @Test
    void updateStatut_shouldUpdateAndSaveWhenFound() {
        Dossier dossier = Dossier.builder()
                .id(1L)
                .statut(StatutDossier.EN_ATTENTE_DOCUMENTS)
                .build();

        when(dossierRepository.findById(1L)).thenReturn(Optional.of(dossier));
        when(dossierRepository.save(any(Dossier.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Dossier updated = dossierService.updateStatut(1L, StatutDossier.ACCEPTE);

        assertNotNull(updated);
        assertEquals(StatutDossier.ACCEPTE, updated.getStatut());

        verify(dossierRepository, times(1)).findById(1L);
        verify(dossierRepository, times(1)).save(dossier);
    }

    @Test
    void updateStatut_shouldReturnNullWhenNotFound() {
        when(dossierRepository.findById(99L)).thenReturn(Optional.empty());

        Dossier updated = dossierService.updateStatut(99L, StatutDossier.REFUSE);

        assertNull(updated);
        verify(dossierRepository, times(1)).findById(99L);
        verify(dossierRepository, never()).save(any(Dossier.class));
    }
}