package com.m_motors.mmotors.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DossierTest {

    @Test
    void dossier_getters_setters() {
        Dossier d = new Dossier();

        d.setId(1L);

        assertEquals(1L, d.getId());
    }
}