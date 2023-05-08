package com.example.demo.controllers;

import com.example.demo.entities.Bibliotheque;
import com.example.demo.entities.Document;
import com.example.demo.services.BiblioService;
import com.example.demo.services.DocService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.Collection;

@RestController
@RequestMapping("/Biblio")
@AllArgsConstructor
public class BiblioController {
    private DocService docService;

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/")
    public Collection<Document> getDocs(){
        return docService.findAll();
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/categories/{categ}/")
    public Collection<Document> getDocs(@PathVariable String categ){
        return docService.getDocsByCategorie(categ);
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/book/{id}/")
    public Document getDocsById(@PathVariable Long id){
        return docService.getDocsById(id);
    }

    @CrossOrigin("http://localhost:4200")
    @DeleteMapping("/delete/{id}/")
    public void deleteDocById(@PathVariable Long id){
        docService.deleteDocById(id);
    }
}
