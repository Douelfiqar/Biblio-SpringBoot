package com.example.demo.repositories;

import com.example.demo.entities.Bibliotheque;
import com.example.demo.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface BiblioRepo extends JpaRepository<Bibliotheque, Long> {
    @Query("select b.documentCollection FROM Bibliotheque b where b.id = :id")
    public Collection<Document> findDocumentById(@Param("id") Long id);
}
