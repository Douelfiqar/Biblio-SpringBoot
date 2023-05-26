package com.example.demo.repositories;


import com.example.demo.entities.Document;
import com.example.demo.entities.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.Collection;
import java.util.Date;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {
    public Collection<Document> findDocumentByCategorie(String categorie);
    public Document findDocumentById(Long id);
    public void deleteDocumentById(Long id);
    @Query("FROM Document d order by d.prixLocation ASC")
    public Collection<Document> findAllDocs();
    @Query("SELECT d FROM Document d WHERE d.titre LIKE CONCAT('%', :term, '%')")
    public Collection<Document> SearchDocument(String term);

    @Query("select count(*) from Pret where document.id IN (select id from Document where categorie = :categorie)")
    public int NmbrePretDocument(String categorie);
    //@Query("SELECT doc FROM Document doc WHERE doc.id IN ( SELECT p.id FROM Pret p WHERE p.dateDebutPretation BETWEEN :depart AND :fin)")
    //@Query("SELECT d FROM Document d WHERE d.id IN (SELECT p.document.id FROM Pret p WHERE p.dateDebutPretation >= :depart AND p.dateDebutPretation <= :fin)")
    @Query("SELECT count(*) FROM Pret p WHERE p.dateDebutPretation >= :depart AND p.dateDebutPretation <= :fin AND p.document.id = :idDoc")
    public int raport(Date depart, Date fin, Long idDoc);

    //@Query("SELECT d FROM Document d WHERE d.id IN (SELECT l.document.id FROM Location l WHERE l.dateDebutLocation >= :depart AND l.dateDebutLocation <= :fin)")
    @Query("SELECT count(l.document.id) FROM Location l WHERE l.dateDebutLocation >= :depart AND l.dateDebutLocation <= :fin AND l.document.id = :idDoc")
    public int raportLocation(Date depart, Date fin, Long idDoc);
}
