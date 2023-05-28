package com.example.demo.repositories;

import com.example.demo.entities.Document;
import com.example.demo.entities.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public interface PreterRepo extends JpaRepository<Pret, Long> {
    @Query("SELECT d FROM Document d WHERE d.id IN (SELECT p.document.id FROM Pret p WHERE p.adherent.id = :idAdherent AND p.rendu = false)")
    public Collection<Document> selectionDocsPreter(Long idAdherent);

    @Modifying
    @Query("UPDATE Pret p SET p.rendu = true WHERE p.document.id = :idDocument AND p.adherent.id = :idAdherent")
    public void renduPret(Long idDocument, Long idAdherent);

    @Query("SELECT p.amende FROM Pret p WHERE p.document.id = :idDocument AND p.adherent.id = :idClient")
    public double getAmende(Long idDocument, Long idClient);
}
