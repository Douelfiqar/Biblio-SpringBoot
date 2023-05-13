package com.example.demo.repositories;


import com.example.demo.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.Collection;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {
    public Collection<Document> findDocumentByCategorie(String categorie);
    public Document findDocumentById(Long id);
    public void deleteDocumentById(Long id);
    @Query("FROM Document d order by d.prixLocation ASC")
    public Collection<Document> findAllDocs();
    @Query("SELECT d FROM Document d WHERE d.titre LIKE CONCAT('%', :term, '%')")
    public Collection<Document> SearchDocument(String term);
}
