package com.example.demo.controllers;

import com.example.demo.entities.Adherent;
import com.example.demo.entities.Bibliotheque;
import com.example.demo.entities.Client;
import com.example.demo.entities.Document;
import com.example.demo.repositories.ReservationRepo;
import com.example.demo.services.*;
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
    private LocationService locationService;
    private ClientService clientService;
    private PreterService preterService;
    private AdherentService adherentService;
    private ReservationService reservationService;
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

    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/Search")
    public Collection<Document> SearchDocument(@RequestParam("term") String term){
        return docService.SearchDocument(term);
    }
    @CrossOrigin("http://localhost:4200")
    @PostMapping("/updateProduct/")
    public void updateDocs(@RequestBody Document document){

        Document document1 = docService.getDocsById(document.getId());

        document1.setAuteur(document.getAuteur());
        document1.setSujet(document.getSujet());
        document1.setTitre(document.getTitre());
        document1.setCategorie(document.getCategorie());
        document1.setNombreExemplaire(document.getNombreExemplaire());
        document1.setShortDesc(document.getShortDesc());
        document1.setPrixLocation(document.getPrixLocation());
        document1.setLinkImage(document.getLinkImage());
        document1.setDateEdition(document.getDateEdition());

        Bibliotheque bibliotheque = biblioService.getBiblio();

        document1.setBibliotheque(bibliotheque);
        docService.saveDoc(document1);
    }

    @GetMapping("/addLocation")
    public void addLocation(@RequestParam("idDocument") String idDocument, @RequestParam("cinClient") String cinClient){
        locationService.addLocation(Long.parseLong(idDocument), cinClient);
    }

    @GetMapping("/getClients")
    public Collection<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/getAdherents")
    public Collection<Adherent> getAdherents(){
        return adherentService.getAllAdherent();
    }

    @GetMapping("/addPret")
    public void addPret(@RequestParam("idDocument") String idDocument, @RequestParam("codeAdherent") String codeAdherent){
        preterService.addPretation(Long.parseLong(idDocument), codeAdherent);
    }

    @GetMapping("/NombreDoc")
    public int NmbrePretDocument(@RequestParam("categorie") String categorie){
        int nombrePret = docService.NmbrePretDocument(categorie);
        return nombrePret;
    }

    @GetMapping("/addReservation")
    public void addReservation(@RequestParam("idDocument") String idDocument, @RequestParam("cinClient") String cinClient){
        reservationService.addReservation(Long.parseLong(idDocument), cinClient);
    }

    @PostMapping("/addUser")
    public void addClient(@RequestBody Client client){
        clientService.addClient(client);
    }
    @PostMapping("/addAdherent")
    public void addClient(@RequestBody Adherent adherent){
        adherentService.addAdherent(adherent);
    }


}
