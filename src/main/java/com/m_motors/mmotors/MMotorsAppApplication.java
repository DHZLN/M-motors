package com.m_motors.mmotors; // ou le package correct de ce fichier

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// AJOUTE CETTE LIGNE POUR FORCER LE SCAN
@ComponentScan(basePackages = {"com.m_motors.mmotors"})
public class MMotorsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MMotorsAppApplication.class, args);
    }
}