package com.example.demo.metier;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class BibliothequeServiceImp implements BibliothequeService {
    @Autowired
    private ClientRepo clientRepo;
    private DocumentRepo documentRepo;
    private LocationRepo locationRepo;
    private PretRepo pretRepo;
    private ReservationRepo reservationRepo;
    @Override
    public Client ajouterClient(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public void supprimerClient(Long idClient) {
        clientRepo.deleteById(idClient);

    }

    @Override
    public Client modifierClient(Long idClient, Client client) {

        Optional<Client> optionalClient = clientRepo.findById(idClient);
        if (optionalClient.isPresent()) {
            Client clientExist = optionalClient.get();
            clientExist.setNom(client.getNom());
            clientExist.setPrenom(client.getPrenom());
            clientExist.setAddress(client.getAddress());
            clientExist.setEmail(client.getEmail());
            clientExist.setNumTelephone(client.getNumTelephone());
            return clientRepo.save(clientExist);
        } else {
            throw new EntityNotFoundException("Client avec l'identifiant " + idClient + " n'existe pas !");
        }
    }

    @Override
    public Client afficherClient(Long idClient) {
        Optional<Client> optionalClient = clientRepo.findById(idClient);
        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new EntityNotFoundException("Client avec l'identifiant " + idClient + " n'existe pas !");
        }
    }

    @Override
    public Document ajouterDocument(Document document) {

        return documentRepo.save(document);
    }

    @Override
    public void supprimerDocument(Long idDocument) {
        Optional<Document> optionalDocument = documentRepo.findById(idDocument);
        if (optionalDocument.isPresent()) {
            documentRepo.deleteById(idDocument);
        } else {
            throw new EntityNotFoundException("Document avec l'identifiant " + idDocument + " n'existe pas !");
        }
    }

    @Override
    public Document modifierDocument(Document document) {
        Optional<Document> optionalDocument = documentRepo.findById(document.getId());
        if (optionalDocument.isPresent()) {
            Document doc = optionalDocument.get();
            doc.setTitre(document.getTitre());
            doc.setAuteur(document.getAuteur());
            doc.setSujet(document.getSujet());
            doc.setCategorie(document.getCategorie());
            doc.setPrixLocation(document.getPrixLocation());
            doc.setNombreExemplaire(document.getNombreExemplaire());
            doc.setDateEdition(document.getDateEdition());
            return documentRepo.save(doc);
        } else {
            throw new EntityNotFoundException("Document avec l'identifiant " + document.getId() + " n'existe pas !");
        }
    }

    @Override
    public Document afficherDocument(Long idDocument) {
        Optional<Document> optionalDocument = documentRepo.findById(idDocument);
        if (optionalDocument.isPresent()) {
            return optionalDocument.get();
        } else {
            throw new EntityNotFoundException("Document avec l'identifiant " + idDocument + " n'existe pas !");
        }
    }
    public Location louer(Client client, Document document) {
        Location location = new Location();
        location.setClient(client);
        location.setDocument(document);
        location.setDateLocation(new Date());
        document.setNombreExemplaire(document.getNombreExemplaire() - 1);
        return locationRepo.save(location);
    }
    public Pret preter(Adherent adherent, Document document) {
        Pret pret = new Pret();
        pret.setAdherent(adherent);
        pret.setDocument(document);
        pret.setDatePret(new Date());
        document.setNombreExemplaire(document.getNombreExemplaire() - 1);
        adherent.setNmbreAllocationEnCours(adherent.getNmbreAllocationEnCours() + 1);
        return pretRepo.save(pret);
    }

    public Reservation reserver(Client client, Document document) {
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setDocument(document);
        reservation.setDate(new Date());
        return reservationRepo.save(reservation);

    }
}
