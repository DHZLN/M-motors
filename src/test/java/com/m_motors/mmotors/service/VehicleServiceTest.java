package com.m_motors.mmotors.service;

import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleServiceTest {

    private VehicleRepository vehicleRepository;
    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        vehicleRepository = mock(VehicleRepository.class);
        vehicleService = new VehicleService(vehicleRepository);
    }

    @Test
    void rechercherVehicules_shouldReturnMatchingVehicles() {
        Vehicle vehicle = Vehicle.builder()
                .id(1L)
                .marque("BMW")
                .modele("X1")
                .annee(2022)
                .kilometrage(20000)
                .typeOffre(TypeOffre.LLD)
                .build();

        when(vehicleRepository.rechercherVehicules("BMW", "X1", TypeOffre.LLD))
                .thenReturn(List.of(vehicle));

        List<Vehicle> result = vehicleService.rechercherVehicules("BMW", "X1", TypeOffre.LLD);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("BMW", result.get(0).getMarque());
        assertEquals("X1", result.get(0).getModele());

        verify(vehicleRepository, times(1))
                .rechercherVehicules("BMW", "X1", TypeOffre.LLD);
    }

    @Test
    void rechercherVehicules_shouldConvertBlankStringsToNull() {
        when(vehicleRepository.rechercherVehicules(null, null, null))
                .thenReturn(List.of());

        List<Vehicle> result = vehicleService.rechercherVehicules("", "   ", null);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(vehicleRepository, times(1))
                .rechercherVehicules(null, null, null);
    }

    @Test
    void getAllVehicules_shouldReturnAllVehicles() {
        Vehicle v1 = Vehicle.builder().id(1L).marque("BMW").modele("Serie 5").build();
        Vehicle v2 = Vehicle.builder().id(2L).marque("Audi").modele("Q5").build();

        when(vehicleRepository.findAll()).thenReturn(List.of(v1, v2));

        List<Vehicle> result = vehicleService.getAllVehicules();

        assertEquals(2, result.size());
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    void getVehiculeById_shouldReturnVehicleWhenFound() {
        Vehicle vehicle = Vehicle.builder().id(1L).marque("Mercedes").modele("Classe E").build();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        Vehicle result = vehicleService.getVehiculeById(1L);

        assertNotNull(result);
        assertEquals("Mercedes", result.getMarque());
        verify(vehicleRepository, times(1)).findById(1L);
    }

    @Test
    void getVehiculeById_shouldReturnNullWhenNotFound() {
        when(vehicleRepository.findById(99L)).thenReturn(Optional.empty());

        Vehicle result = vehicleService.getVehiculeById(99L);

        assertNull(result);
        verify(vehicleRepository, times(1)).findById(99L);
    }

    @Test
    void saveVehicule_shouldSaveAndReturnVehicle() {
        Vehicle vehicle = Vehicle.builder()
                .marque("Porsche")
                .modele("Macan")
                .typeOffre(TypeOffre.LLD)
                .build();

        Vehicle savedVehicle = Vehicle.builder()
                .id(10L)
                .marque("Porsche")
                .modele("Macan")
                .typeOffre(TypeOffre.LLD)
                .build();

        when(vehicleRepository.save(ArgumentMatchers.any(Vehicle.class)))
                .thenReturn(savedVehicle);

        Vehicle result = vehicleService.saveVehicule(vehicle);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Porsche", result.getMarque());
        verify(vehicleRepository, times(1)).save(vehicle);
    }
}