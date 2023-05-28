package com.example.demo.services;

import com.example.demo.entities.Adherent;
import com.example.demo.entities.Document;
import com.example.demo.entities.Location;
import com.example.demo.entities.Pret;
import com.example.demo.repositories.LocationRepo;
import com.example.demo.repositories.PreterRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@Service
public class PreterService {
    private PreterRepo preterRepo;
    private AdherentService adherentService;
    private DocService docService;
    public void addPretation(Long idDocument, String codeAdherent) {

        Pret pret = new Pret();

        pret.setDateDebutPretation(new Date());

        // Set dateFinLocation to current date
        pret.setDateFinPretation(new Date());

        // Add 7 days
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pret.getDateFinPretation());
        calendar.add(Calendar.DAY_OF_YEAR, 21);
        pret.setDateFinPretation(calendar.getTime());

        Adherent adherent = adherentService.findAdherentByCodeAdherent(codeAdherent);
        int nmbre_allocation = adherent.getNmbreAllocationEnCours() + 1;
        adherent.setNmbreAllocationEnCours(nmbre_allocation);
        pret.setAdherent(adherent);
        Document document = docService.getDocsById(idDocument);
        pret.setDocument(document);
        adherentService.addAdherent(adherent);
        preterRepo.save(pret);

        document.setNombreExemplaire(document.getNombreExemplaire() - 1);

        docService.saveDoc(document);
    }

    public Collection<Document> selectionDocsPreter(Long idAdherent){
        return preterRepo.selectionDocsPreter(idAdherent);
    }

    public void renduPret(Long idDocument, Long idAdherent){
        preterRepo.renduPret(idDocument, idAdherent);
    }

    public Collection<Pret> findAllPreter(){
        return preterRepo.findAll();
    }

    public void savePret(Pret pret){
        preterRepo.save(pret);
    }
    public double getAmende(Long idDocument, Long idClient){
        return preterRepo.getAmende(idDocument, idClient);
    }
}
