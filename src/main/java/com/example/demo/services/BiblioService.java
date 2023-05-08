package com.example.demo.services;

import com.example.demo.entities.Bibliotheque;
import com.example.demo.entities.Document;
import com.example.demo.repositories.BiblioRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class BiblioService {
    private BiblioRepo biblioRepo;

    public Bibliotheque saveBiblio(Bibliotheque bibliotheque){
        return biblioRepo.save(bibliotheque);
    }

    public Collection<Bibliotheque> findAll(){
        return biblioRepo.findAll();
    }

    public Collection<Document> getAllDoc(Long id){
        return biblioRepo.findDocumentById(id);
    }

    public Bibliotheque getBiblio() {
        return biblioRepo.findById(8L).get();
    }




}
