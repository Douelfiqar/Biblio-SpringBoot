package com.example.demo.services;

import com.example.demo.entities.Document;
import com.example.demo.repositories.DocumentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Service
@AllArgsConstructor
@Transactional
public class DocService {
    private DocumentRepo documentRepo;

    public Document saveDoc(Document doc){
        return documentRepo.save(doc);
    }

    public Collection<Document> findAll(){
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
}
