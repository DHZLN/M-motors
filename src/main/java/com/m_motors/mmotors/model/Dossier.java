package com.m_motors.mmotors.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dossiers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicule_id", nullable = false)
    private Vehicle vehicule;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOffre typeOffre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
private StatutDossier statut = StatutDossier.EN_ATTENTE_DOCUMENTS;

    // Options LLD
    private Boolean assuranceTousRisques;
    private Boolean assistanceDepannage;
    private Boolean entretienEtSav;
    private Boolean controleTechniqueInclus;

    private LocalDateTime dateCreation;
    private LocalDateTime dateDerniereMiseAJour;

    @OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents;

    @PrePersist
    public void prePersist() {
        dateCreation = LocalDateTime.now();
        dateDerniereMiseAJour = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        dateDerniereMiseAJour = LocalDateTime.now();
    }
}