package com.example.demo.controllers;

import com.example.demo.entities.Bibliotheque;
import com.example.demo.entities.Document;
import com.example.demo.services.BiblioService;
import com.example.demo.services.DocService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/Biblio")
@AllArgsConstructor
public class BiblioController {
    private DocService docService;
    private BiblioService biblioService;
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/")
    public Collection<Document> getDocs(){

        return docService.findAllDocs();
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

        @CrossOrigin(origins = "http://localhost:4200")
        @PostMapping("/addProduct")
        public void addDocs(@RequestBody Document document){
        Bibliotheque bibliotheque = biblioService.getBiblio();
        Document document1 = new Document();
        document1.setAuteur(document.getAuteur());
        document1.setSujet(document.getSujet());
        document1.setTitre(document.getTitre());
        document1.setLinkImage(document.getLinkImage());
        document1.setCategorie(document.getCategorie());
        document1.setShortDesc(document.getShortDesc());
        document1.setPrixLocation(document.getPrixLocation());
        document1.setNombreExemplaire(document.getNombreExemplaire());
        document1.setDateEdition(document.getDateEdition());
        document1.setBibliotheque(bibliotheque);
        docService.saveDoc(document1);
            System.out.printf("done");
        }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/Search")
    public Collection<Document> SearchDocument(@RequestParam("term") String term){
        return docService.SearchDocument(term);
    }

}
