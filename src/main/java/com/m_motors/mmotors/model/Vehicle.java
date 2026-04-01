package com.mmotors.m_motors_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La marque est obligatoire")
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    private String modele;

    private Integer annee;
    private Integer kilometrage;
    private Double prixAchat;
    private Double loyerMensuel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOffre typeOffre; // ACHAT ou LLD

    private String photoUrl;
    private String description;

    // Options pour la LLD
    private Boolean assuranceIncluse;
    private Boolean assistanceIncluse;
    private Boolean entretienInclu;
    private Boolean controleTechniqueInclus;

    @OneToMany(mappedBy = "vehicule")
    private List<Dossier> dossiers;
}