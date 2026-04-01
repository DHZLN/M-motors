package com.mmotors.m_motors_app.repository;

import com.mmotors.m_motors_app.model.Document;
import java.util.List;
import java.util.Optional;

public interface DocumentRepository {
    Document save(Document document);
    Optional<Document> findById(Long id);
    List<Document> findByDossierId(Long dossierId);
}