package com.example.demo.repositories;

import com.example.demo.entities.Document;
import com.example.demo.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
@Transactional
public interface LocationRepo extends JpaRepository<Location, Long> {
    @Query("SELECT d FROM Document d WHERE d.id IN (SELECT l.document.id FROM Location l WHERE l.client.id = :idClient and l.rendu = false)")
    public Collection<Document> selectionDocsLouer(Long idClient);
    //Collection<Location> findAllBy
    @Modifying
    @Query("UPDATE Location l set l.rendu = true WHERE l.client.id = :idClient AND l.document.id = :idDocument")
    public void renduLocation(Long idDocument, Long idClient);

    @Query("SELECT l.amende FROM Location l WHERE l.document.id = :idDocument AND l.client.id = :idClient")
    public double getAmende(Long idDocument, Long idClient);
}
