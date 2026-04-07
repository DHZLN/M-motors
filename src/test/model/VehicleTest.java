package com.m_motors.mmotors.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void vehicle_getters_setters() {
        Vehicle v = new Vehicle();

        v.setMarque("BMW");
        v.setModele("X5");

        assertEquals("BMW", v.getMarque());
        assertEquals("X5", v.getModele());
    }
}