package com.example.demo.controllers;

import com.example.demo.entities.*;
import com.example.demo.repositories.ReservationRepo;
import com.example.demo.services.*;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

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
    @GetMapping("/")
    public Collection<Document> getDocs(){

        return docService.findAllDocs();
    }

    @GetMapping("/categories/{categ}/")
    public Collection<Document> getDocs(@PathVariable String categ){
        return docService.getDocsByCategorie(categ);
    }

    @GetMapping("/book/{id}/")
    public Document getDocsById(@PathVariable Long id){
        return docService.getDocsById(id);
    }

    @DeleteMapping("/delete/{id}/")
    public void deleteDocById(@PathVariable Long id){
        docService.deleteDocById(id);
    }

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

    @GetMapping("/Search")
    public Collection<Document> SearchDocument(@RequestParam("term") String term){
        return docService.SearchDocument(term);
    }
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

    @PostMapping("/addUser/")
    public void addClient(@RequestBody Client client){
        clientService.addClient(client);
    }
    @PostMapping("/addAdherent/")
    public void addClient(@RequestBody Adherent adherent){
        adherentService.addAdherent(adherent);
    }

    @DeleteMapping("/deleteClient")
    public void deleteClientById(@RequestParam("id") String id){
        clientService.deleteClientById(Long.parseLong(id));
    }

    @GetMapping("/searchClients")
    public Collection<Client> SearchClient(@RequestParam("term") String term){
        return clientService.SearchClient(term);
    }
    @GetMapping("/NmbrePret")
    public int rapport(@RequestParam("depart") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date depart,
                                    @RequestParam("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin, @RequestParam("idDoc") Long idDoc) {
        return docService.rapport(depart, fin, idDoc);
    }

    @GetMapping("/NmbreLocation")
    public int rapportLocation(@RequestParam("depart") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date depart,
                                        @RequestParam("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin, @RequestParam("idDoc") Long idDoc) {
        return docService.raportLocation(depart, fin, idDoc);
    }

    @GetMapping("/ListPret")
    public Collection<Document> selectionDocsPreter(@RequestParam("idAdherent") Long idAdherent){
        return preterService.selectionDocsPreter(idAdherent);
    }

    @GetMapping("/ListLocation")
    public Collection<Document> selectionDocsLouer(@RequestParam("idClient") Long idClient){
        return locationService.selectionDocsLouer(idClient);
    }
    @GetMapping("/isAdherent")
    public boolean isAdherentById(@RequestParam("idAdherent")Long id) {
        Optional<Adherent> adherentOptional = adherentService.getAdherentById(id);
        return adherentOptional.isPresent();
    }

    @GetMapping("/renduLocation")
    public void renduLocation(@RequestParam("idDocument")Long idDocument,@RequestParam("idClient") Long idClient) {
        locationService.renduLocation(idDocument, idClient);
    }

    @GetMapping("/renduPret")
    public void renduPret(@RequestParam("idDocument")Long idDocument, @RequestParam("idAdherent")Long idAdherent) {
        preterService.renduPret(idDocument, idAdherent);
    }

}
