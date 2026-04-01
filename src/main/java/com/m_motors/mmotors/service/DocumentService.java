package com.mmotors.m_motors_app.service;

import com.mmotors.m_motors_app.model.Document;
import com.mmotors.m_motors_app.repository.DocumentRepository;

public class DocumentService { // Pas @Service pour l'instant
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document uploadDocument(Object file, Long dossierId) { // Object file temporaire
        System.out.println("Upload de document pour dossier : " + dossierId);
        return null;
    }
}