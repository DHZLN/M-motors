package com.mmotors.m_motors_app.controller;

public class DossierController { // Pas @Controller pour l'instant
    public String showDossierForm() {
        return "dossier/creer";
    }

    public String clientDashboard() {
        return "client/dashboard";
    }
}