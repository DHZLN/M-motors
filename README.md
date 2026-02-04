# M-motors
🚗 M-Motors – Application de gestions et de ventes de véhicules
---
📌 Présentation du projet

M-Motors est une application web dédiée à la vente de véhicules d’occasion et à la location longue durée avec option d’achat (LLD/LOA).

Ce projet est réalisé dans le cadre du Bachelor Développeur d’Application Java – STUDI.
Il s’inscrit dans une stratégie de diversification des revenus de l’entreprise M-Motors et de modernisation de ses processus métiers, notamment par la dématérialisation complète des dossiers clients.

---
## 🎯 Enjeux & objectifs métier

- Introduire une nouvelle offre LLD avec option d’achat
- Maintenir l’activité historique de vente de véhicules
- Réduire les coûts opérationnels liés aux dossiers papier
- Améliorer l’expérience client grâce à un espace personnel
- Garantir la sécurité, la traçabilité et la continuité de service
- Concevoir une solution scalable et évolutive hébergée dans le cloud

---

## ⚙️ Fonctionnalités principales (MVP)

## 👤 Gestion des utilisateurs
- Création de compte client
- Authentification sécurisée
- Accès différencié selon le rôle (client / back-office)

## 🚘 Recherche & catalogue véhicules
- Recherche de véhicules à l’achat ou en location longue durée
- Consultation détaillée des fiches véhicules
- Distinction claire des types d’offres

## 📄 Gestion des dossiers clients
- Dépôt de dossier d’achat ou de location en ligne
- Téléversement des documents justificatifs
- Dossier 100 % dématérialisé

## 🧾 Suivi des dossiers
- Consultation de l’état d’avancement du dossier depuis l’espace client
- Réduction des sollicitations du service commercial

## 🏢 Back-office commercial
- Ajout et gestion des véhicules (vente / location)
- Consultation des dossiers clients
- Validation ou refus des dossiers

## 🔐 Sécurité & continuité
- Authentification via JWT (JSON Web Token)
- Protection des données clients
- Préparation aux exigences RPO / RTO

  ---

## 🧠 Méthodologie de gestion de projet

- Méthodologie Agile : Scrum
- Livraison incrémentale via MVP
- Découpage fonctionnel par EPICs
- Gestion du backlog produit
- Prise en compte des notions :
   - Definition of Ready (DoR)
   - Definition of Done (DoD)

---

## 🏗️ Architecture générale
- Front-end : React (SPA)
- Back-end : API REST Spring Boot
- Base de données : MySQL (relationnelle)
- Stockage documents : Google Cloud Storage (GCS)
- Hébergement : Google Cloud Platform (GCP)
- Déploiement : Heroku

Architecture conçue pour :
 - Séparation front / back
 - Scalabilité
 - Évolutivité fonctionnelle
 - Intégrations futures (signature électronique, reporting, automatisation)
   
--- 
## 🛠️ Technologies utilisées

## Langages
- Java
- JavaScript
- HTML
- CSS
  
## Outils de développement :
- Vs Code
  
## Frameworks & bibliothèques

- React – Interface utilisateur dynamique
- Spring Boot  : Framework Web
- Spring Security : Framework de sécurité
- Spring Data JPA – Framework de gestion de base de données
- JWT – Gestion des accès

## Base de données & stockage

- MySQL – Données structurées (clients, véhicules, dossiers)
- Google Cloud Storage – Documents dématérialisés

## Outils & DevOps*

- Git / GitHub
- GitHub Actions (CI/CD)
- Jira Software
- Microsoft Teams
  ---

## 🚀 Installation & démarrage
## Prérequis

- Java 17 ou +
- Node.js
- MySQL
- Maven
- Git

  ## Installation :
 1. Cloner le dépôt
     ``` https://github.com/DHZLN/M-motors  ```
    2. Configurer la base de données MySQL
    3. Renseigner les variables dans :
    4.  ``` backend/src/main/resources/application.yml ```
        ``` spring:
             datasource :
               url : jdbc:mysql://localhost:3306/motors
               username : your_username
               password : your_password

            jwt:
              secret : your_secret_key  ```
    





  
  

