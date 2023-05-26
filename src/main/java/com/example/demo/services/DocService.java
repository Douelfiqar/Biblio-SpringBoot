package com.example.demo.services;

import com.example.demo.entities.Document;
import com.example.demo.entities.Pret;
import com.example.demo.repositories.DocumentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.print.Doc;
import java.util.Collection;
import java.util.Date;

@Service
@AllArgsConstructor
@Transactional
public class DocService {
    private DocumentRepo documentRepo;

    public Document saveDoc(Document doc){
        return documentRepo.save(doc);
    }

    public Collection<Document> findAllDocs(){
        return documentRepo.findAll();
    }

    public Collection<Document> getDocsByCategorie(String categorie){
        return documentRepo.findDocumentByCategorie(categorie);
    }

    public Document getDocsById(Long id){
        return documentRepo.findDocumentById(id);
    }

    public void deleteDocById(Long id){
        documentRepo.deleteDocumentById(id);
    }

    public Collection<Document> SearchDocument(String term){
        return documentRepo.SearchDocument(term);
    }

    public int NmbrePretDocument(String categorie){
        return documentRepo.NmbrePretDocument(categorie);
    }

    public int rapport(Date depart, Date fin, Long idDoc){
        return documentRepo.raport(depart, fin, idDoc);
    }
    public int raportLocation(Date depart, Date fin, Long idDoc){
        return documentRepo.raportLocation(depart, fin, idDoc);
    }
}
